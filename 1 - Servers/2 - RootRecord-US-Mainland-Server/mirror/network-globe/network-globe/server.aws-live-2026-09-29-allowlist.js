
const express = require('express');
const cors = require('cors');
const path = require('path');
const fs = require('fs');
const readline = require('readline');

const app = express();
const PORT = process.env.PORT || 8080;
const FEED = path.join(__dirname, 'data', 'hawaii.ndjson');
const WINDOW_MS = 90000;
const MAX_ARCS = 150;
const GEO_CACHE_FILE = path.join(__dirname, 'data', 'geo-cache.json');

app.use(cors());
app.use(express.json());
// SECURITY (2026-09-29 HST): no root static serve. express.static(__dirname) exposed
// server.js, package*.json, README, data/hawaii.ndjson, the sqlite history, scripts, etc.
// Only an explicit allowlist is served; everything else is 404.
app.disable('x-powered-by');
const INDEX_FILE = path.join(__dirname, 'index.html');
const OVERLAY_DIR = path.join(__dirname, 'overlay');
const OVERLAY_ALLOW = new Set(['overlay.js', 'overlay.css', 'overlay-config.json']);
function notFound(res) { res.status(404).type('text/plain').send('not found\n'); }
function sendIndex(req, res) {
  res.set('Cache-Control', 'no-cache');
  res.sendFile(INDEX_FILE);
}
app.get('/', sendIndex);
app.get('/index.html', sendIndex);
app.get('/overlay/:file', function(req, res) {
  const f = req.params.file;
  if (!OVERLAY_ALLOW.has(f)) return notFound(res);
  const full = path.join(OVERLAY_DIR, f);
  fs.stat(full, function(err, st) {
    if (err || !st.isFile()) return notFound(res);
    res.sendFile(full);
  });
});

let origin = { lat: 21.3069, lng: -157.8583, label: 'Hawaii' };
const flows = new Map();
const geoCache = new Map();
let geoQueue = [];
let geoBusy = false;
let feedOffset = 0;

try {
  if (fs.existsSync(GEO_CACHE_FILE)) {
    const raw = JSON.parse(fs.readFileSync(GEO_CACHE_FILE, 'utf8'));
    for (const [ip, v] of Object.entries(raw)) geoCache.set(ip, v);
    console.log('Loaded geo cache:', geoCache.size);
  }
} catch (e) {}

function saveGeoCache() {
  try { fs.writeFileSync(GEO_CACHE_FILE, JSON.stringify(Object.fromEntries(geoCache))); } catch (e) {}
}

async function lookupGeo(ip) {
  if (!ip || geoCache.has(ip)) return geoCache.get(ip);
  try {
    const r = await fetch('http://ip-api.com/json/' + encodeURIComponent(ip) + '?fields=status,lat,lon,city,country,org', { signal: AbortSignal.timeout(4000) });
    const d = await r.json();
    if (d.status === 'success' && Number.isFinite(d.lat) && Number.isFinite(d.lon)) {
      const entry = { lat: d.lat, lng: d.lon, city: d.city || '', country: d.country || '', org: d.org || '' };
      geoCache.set(ip, entry);
      return entry;
    }
  } catch (e) {}
  geoCache.set(ip, { lat: null, lng: null, city: '', country: '', org: '' });
  return geoCache.get(ip);
}

function enqueueGeo(ip) {
  if (!ip || geoCache.has(ip) || geoQueue.includes(ip)) return;
  geoQueue.push(ip);
}

async function processGeoQueue() {
  if (geoBusy || geoQueue.length === 0) return;
  geoBusy = true;
  while (geoQueue.length) {
    await lookupGeo(geoQueue.shift());
    await new Promise(r => setTimeout(r, 1100));
  }
  saveGeoCache();
  geoBusy = false;
}

function flowKey(rec) {
  const d = rec.destination || {};
  return (rec.protocol || '') + '|' + (d.ip || '') + ':' + (d.port || 0) + '|' + (rec.process || '');
}

function ingestRecord(rec) {
  if (!rec || rec.type !== 'network-globe-telemetry') return;
  const now = Date.now();
  const src = rec.source || {};
  if (Number.isFinite(src.latitude) && Number.isFinite(src.longitude)) {
    origin = { lat: src.latitude, lng: src.longitude, label: src.label || 'Hawaii' };
  }
  const key = flowKey(rec);
  const prev = flows.get(key);
  flows.set(key, {
    rec: rec,
    lastSeen: now,
    packets: (prev ? prev.packets : 0) + (Number(rec.packets) || 0),
    bytes: (prev ? prev.bytes : 0) + (Number(rec.bytes) || 0)
  });
  if (rec.destination && rec.destination.ip) enqueueGeo(rec.destination.ip);
}

function pruneFlows() {
  const cutoff = Date.now() - WINDOW_MS;
  for (const [k, v] of flows) {
    if (v.lastSeen < cutoff) flows.delete(k);
  }
}

function buildState() {
  pruneFlows();
  const arcs = [];
  const points = [{ lat: origin.lat, lng: origin.lng, label: origin.label || 'Hawaii', type: 'origin' }];
  const endpoints = new Set();
  let packetRate = 0;
  let bytesPerSec = 0;
  const windowSec = WINDOW_MS / 1000;

  for (const item of flows.values()) {
    const rec = item.rec;
    const dest = rec.destination || {};
    const ip = dest.ip;
    if (!ip) continue;
    endpoints.add(ip);
    packetRate += (item.packets || 0) / windowSec;
    bytesPerSec += (item.bytes || 0) / windowSec;
    const geo = geoCache.get(ip);
    if (!geo || geo.lat == null || geo.lng == null) continue;
    arcs.push({
      startLat: origin.lat,
      startLng: origin.lng,
      endLat: geo.lat,
      endLng: geo.lng,
      process: rec.process || 'network',
      protocol: rec.protocol || '',
      endpoint: ip,
      port: dest.port || '',
      city: geo.city,
      country: geo.country,
      org: geo.org,
      color: '#22c55e',
      altitude: 0.18,
      stroke: 1.2
    });
  }

  const limited = arcs.slice(0, MAX_ARCS);
  const seen = new Set();
  for (const a of limited) {
    if (seen.has(a.endpoint)) continue;
    seen.add(a.endpoint);
    points.push({
      lat: a.endLat,
      lng: a.endLng,
      label: (a.city || a.endpoint) + (a.country ? ', ' + a.country : ''),
      type: 'dest'
    });
  }

  return {
    origin: { label: origin.label || 'Hawaii' },
    stats: {
      activeFlows: flows.size,
      endpoints: endpoints.size,
      packetRate: Math.round(packetRate * 10) / 10,
      bytesPerSec: Math.round(bytesPerSec),
      collector: 'hawaii-feed'
    },
    aws: { ok: false, reason: 'No AWS telemetry connected' },
    arcs: limited,
    points: points
  };
}

function readNewRecords() {
  try {
    if (!fs.existsSync(FEED)) return;
    const st = fs.statSync(FEED);
    if (st.size < feedOffset) feedOffset = 0;
    if (st.size === feedOffset) return;
    const stream = fs.createReadStream(FEED, { start: feedOffset, encoding: 'utf8' });
    const rl = readline.createInterface({ input: stream, crlfDelay: Infinity });
    rl.on('line', function(line) {
      line = line.trim();
      if (!line) return;
      try { ingestRecord(JSON.parse(line)); } catch (e) {}
    });
    rl.on('close', function() { feedOffset = st.size; });
  } catch (e) {
    console.error('feed read', e.message);
  }
}

function bootstrapFeed() {
  try {
    if (!fs.existsSync(FEED)) return;
    const st = fs.statSync(FEED);
    feedOffset = Math.max(0, st.size - 3 * 1024 * 1024);
    readNewRecords();
    console.log('Bootstrapped feed offset', feedOffset, 'size', st.size);
  } catch (e) {
    console.error('bootstrap', e.message);
  }
}

app.get('/health', function(req, res) {
  res.json({ status: 'ok', service: 'network-globe', uptime: process.uptime(), flows: flows.size, geo: geoCache.size });
});

app.get('/api/state', function(req, res) {
  res.json(buildState());
});

app.use(function(req, res) {
  notFound(res);
});

const BIND = process.env.GLOBE_WEB_BIND || '127.0.0.1'; // cloudflared connects locally
app.listen(PORT, BIND, function() {
  console.log('Network Globe listening on', PORT);
  bootstrapFeed();
  setInterval(readNewRecords, 2000);
  setInterval(processGeoQueue, 2500);
  setInterval(saveGeoCache, 60000);
});
