'use strict';

// Public last-known status for api.rootrecord.cloud.
// Reads files AWS already holds. Does not serve a page.
// STATUS_FILE is the Hawaii snapshot. STATE_FILE is the globe process state.
// A missing or unreadable file stays a JSON miss. The previous file is not deleted here.

const http = require('http');
const fs = require('fs');

const HOST = process.env.HOST || '127.0.0.1';
const PORT = Number(process.env.PORT || 8091);
const STATUS_FILE = process.env.STATUS_FILE || '/home/ubuntu/rebroadcast/status-current.json';
const STATE_FILE = process.env.STATE_FILE || '/home/ubuntu/network-globe/network-globe/data/state.json';

function heldAt(file) {
  try {
    return new Date(fs.statSync(file).mtimeMs).toISOString();
  } catch {
    return null;
  }
}

function readJson(file) {
  try {
    const data = JSON.parse(fs.readFileSync(file, 'utf8'));
    if (!data || typeof data !== 'object' || Array.isArray(data)) return null;
    return data;
  } catch {
    return null;
  }
}

function send(res, code, data) {
  const body = JSON.stringify(data);
  res.writeHead(code, {
    'Content-Type': 'application/json; charset=utf-8',
    'Cache-Control': 'no-store',
    'Access-Control-Allow-Origin': '*'
  });
  res.end(body);
}

const server = http.createServer((req, res) => {
  const url = new URL(req.url, `http://${req.headers.host || 'localhost'}`);
  if (req.method !== 'GET' && req.method !== 'HEAD') {
    res.writeHead(405, { 'Content-Type': 'text/plain; charset=utf-8', Allow: 'GET, HEAD' });
    res.end('Method not allowed\n');
    return;
  }
  if (url.pathname === '/health') {
    const statusHeld = heldAt(STATUS_FILE);
    const stateHeld = heldAt(STATE_FILE);
    return send(res, 200, {
      ok: true,
      holder: 'aws',
      status_file: statusHeld !== null,
      status_held_at: statusHeld,
      state_file: stateHeld !== null,
      state_held_at: stateHeld
    });
  }
  if (url.pathname === '/api/status') {
    const snapshot = readJson(STATUS_FILE);
    const held = heldAt(STATUS_FILE);
    if (!snapshot) return send(res, 200, { ok: false, holder: 'aws', detail: 'no_data' });
    return send(res, 200, { ok: snapshot.ok === true, holder: 'aws', held_at: held, snapshot });
  }
  if (url.pathname === '/api/operations') {
    const snapshot = readJson(STATUS_FILE);
    if (!snapshot || snapshot.ok !== true) return send(res, 200, { ok: false, holder: 'aws', detail: 'no_data' });
    return send(res, 200, snapshot);
  }
  if (url.pathname === '/api/state') {
    const state = readJson(STATE_FILE);
    if (!state || !state.stats) return send(res, 200, { ok: false, holder: 'aws', detail: 'no_data' });
    return send(res, 200, state);
  }
  res.writeHead(404, { 'Content-Type': 'text/plain; charset=utf-8' });
  res.end('Not found\n');
});

server.listen(PORT, HOST, () => {
  console.log('status-api listening on ' + HOST + ':' + PORT);
});
