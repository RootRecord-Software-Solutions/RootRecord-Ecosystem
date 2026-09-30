# Evidence — migration-geology pass (2026-09-29, from 13:12 HST)

Desk: rootrecord-software-solutions. All runs at `nice -n 10`, one manual run each, no poller restart (PID 105444 untouched), no delivery, no model load.

## 1. Geology collector — real run 13:19:17 HST (`python3 Geology/scripts/geology_collect.py all`)

```json
{
 "ok": true,
 "at": "2026-09-29T13:19:17-10:00",
 "what": "all",
 "dry_run": false,
 "sources": {
  "hawaii": {
   "ok": true,
   "count": 10,
   "new": 10,
   "new_m2": 1,
   "ms": 633
  },
  "global": {
   "ok": true,
   "count": 33,
   "new": 33,
   "ms": 303
  },
  "hvo_notices": {
   "ok": true,
   "count": 44,
   "new": 44,
   "ms": 1029
  },
  "hvo_status": {
   "ok": true,
   "volcanoes": 8,
   "kilauea": "WATCH",
   "mauna_loa": "NORMAL",
   "ms": 572
  }
 }
}
```

elapsed 2.64 s, max RSS 27 980 KB, rc 0. MemAvailable before 6 961 MB.

### Hawaiʻi (FDSN bbox 18.5–22.5 N, −160.5…−154.5, M≥1.0, last 24 h)

- count 10, M≥2 1, M≥2.5 0, within 150 km of Kīlauea 10, largest {"id": "hv75045217", "mag": 2.35, "place": "14 km S of Fern Forest, Hawaii", "time_hst": "2026-09-28T23:39:39.890000-10:00"}
- new local M≥2 ids this run: ['hv75045217']

| time (HST) | M | type | place | depth km | id |
|---|---|---|---|---|---|
| 2026-09-29T03:39:32.410000-10:00 | 1.68 | ml | 5 km SSE of Pāhala, Hawaii | 32.5 | hv75045322 |
| 2026-09-29T01:14:59.570000-10:00 | 1.78 | md | 1 km WNW of Captain Cook, Hawaii | 14.2 | hv75045252 |
| 2026-09-29T00:48:29.500000-10:00 | 1.76 | md | 7 km SSW of Pāhala, Hawaii | 27.4 | hv75045242 |
| 2026-09-28T23:39:39.890000-10:00 | 2.35 | ml | 14 km S of Fern Forest, Hawaii | 6.2 | hv75045217 |
| 2026-09-28T22:41:37.190000-10:00 | 1.94 | md | 4 km SSW of Pāhala, Hawaii | 32.2 | hv75045192 |
| 2026-09-28T22:01:15.630000-10:00 | 1.84 | md | 27 km W of Volcano, Hawaii | -1.1 | hv75045172 |
| 2026-09-28T20:54:15.240000-10:00 | 1.94 | ml | 6 km SW of Volcano, Hawaii | 0.9 | hv75045142 |
| 2026-09-28T16:10:05.160000-10:00 | 1.75 | md | 19 km SE of Pāhala, Hawaii | 32.5 | hv75045032 |
| 2026-09-28T13:27:44.990000-10:00 | 1.97 | ml | 5 km SSW of Pāhala, Hawaii | 36.6 | hv75044967 |
| 2026-09-28T13:24:23.990000-10:00 | 1.75 | md | 4 km S of Pāhala, Hawaii | 33.2 | hv75044962 |

### Global (summary feed 2.5_day)
- count 33, largest {"id": "us6000tyc3", "mag": 5.6, "place": "southern Mid-Atlantic Ridge", "time_hst": "2026-09-29T03:06:10.191000-10:00"}

| time (HST) | M | place |
|---|---|---|
| 2026-09-29T11:50:29.969000-10:00 | 4.5 | 15 km SW of San Antonio, Colombia |
| 2026-09-29T11:05:56.370000-10:00 | 4.15 | 6 km WNW of Wauna, Washington |
| 2026-09-29T10:45:50.983000-10:00 | 4.7 | 49 km N of Dicabisagan, Philippines |
| 2026-09-29T08:21:49.888000-10:00 | 4.1 | 92 km NE of San Pedro de Atacama, Chile |
| 2026-09-29T06:12:56.037000-10:00 | 4.8 | 44 km NNE of Fangale’ounga, Tonga |

### HVO status (HANS getMonitoredVolcanoes)

| Volcano | Alert level | Color code | Status notice sent (UTC) |
|---|---|---|---|
| Haleakala | NORMAL | GREEN | 2026-09-03 18:41:34 |
| Hualalai | NORMAL | GREEN | 2026-09-03 18:41:34 |
| Kilauea | WATCH | ORANGE | 2026-09-29 19:02:11 |
| Mauna Kea | NORMAL | GREEN | 2026-09-03 18:41:34 |
| Mauna Loa | NORMAL | GREEN | 2026-09-03 18:41:34 |
| Ofu-Olosega | NORMAL | GREEN | 2026-09-03 18:56:27 |
| Ta'u Island | NORMAL | GREEN | 2026-09-03 18:56:27 |
| Tutuila Island | NORMAL | GREEN | 2026-09-03 18:56:27 |

**Kīlauea** — WATCH / ORANGE, headline `WATCH — elevated unrest`, erupting `True`, multiplier 2.5
- latest notice Daily Update 2026-09-29 19:02:11 UTC: “Numerous rapid cycles of south vent overflows and drainback occurred overnight within Halema'uma'u crater at the summit of Kīlauea. The north and south vents had strong glow and intermittent spatter. There is no anomalous activity outside of the summit.”
- latest VAN/VONA 2026-09-29 17:54:03 UTC: “The Halemaʻumaʻu eruption of Kīlauea volcano is currently erupting small overflows from both the north and south vents. It is not possible to forecast the onset of episode 55 fountains or if they will occur.”

**Mauna Loa** — NORMAL / GREEN, headline `quiet`, erupting `None`, multiplier 1.0
- latest notice Monthly Update 2026-09-03 18:41:34 UTC: “Mauna Loa earthquake counts increased in August, while the summit region showed no other signs of deformation.”

### Dedupe + failure path (temp root `/tmp/rr-migr/dbtest`, deleted afterwards)
- 2nd run: `hvo_notices new 0` (44 already in Daily); Daily line counts 10 / 33 / 44 (no duplicates).
- `RR_GEOLOGY_TIMEOUT=0.001`: rc 1, `URLError timed out` recorded in collector-last, `hawaii-last.json` kept (count 10) — failed source never overwrites last good file.

## 2. Earthquake voice report — text only (`voice_reports.py earthquake_report --no-voice`, 13:20:50 HST, out `/tmp/rr-migr/voice-test`, deleted)

- run 1: rc 0, 19 sentences (10 new Hawaiʻi + 12 listed global lines). run 2: 5 sentences:
  > USGS earthquake report at one twenty p.m. Hawaiian Standard Time. No new Hawaii earthquakes since the last report. Hawaii last twenty four hours: 0 magnitude 2.5 or greater. No new global earthquakes since the last report. Global last twenty four hours: 33 magnitude 2.5 or greater.
- `speakers.is_live("earthquake", …)` = True; with no Geology files → "Earthquake data is not on file." and is_live = False (WAV would be skipped).
- WAV not rendered (would load Kokoro — not allowed this pass).

## 3. Kīlauea cams (`kilauea_cams.py`, 13:24:01 HST)

- usgs_v1 [V1cam] West Halemaʻumaʻu: http 200 bytes 325865 sha256 c428f754e017… YouTube `HggWKlZv9yk`
- usgs_v2 [V2cam] North Halemaʻumaʻu: http 200 bytes 188366 sha256 deefa7aae198… YouTube `Tz5tPqRRv1Y`
- usgs_v3 [V3cam] Halemaʻumaʻu lava lake: http 200 bytes 331908 sha256 78cd2c66c5bf… YouTube `gXKuUyKt8mc`
- temp-root 2nd run: all three http 304 (conditional GET, nothing rewritten).

## 4. Earthquake backfill (`earthquakes_backfill.py --days 1 --skip-global`, 13:24 HST)
- quakes.db rows by source: [('hawaii', 11, 2.35)] (git-ignored). Temp root `--days 2`: hawaii 27 + global 99 upserted in ~18 s (1 s polite sleep per chunk).

## 5. Sun times (`Energy/scripts/sun_times.py`, 13:26 HST)
```json
{
 "date": "2026-09-29",
 "sunrise": "06:11",
 "sunset": "18:10",
 "sunrise_iso": "2026-09-29T06:11",
 "sunset_iso": "2026-09-29T18:10",
 "source": "open-meteo",
 "lat": 19.43,
 "lon": -155.23,
 "fetched_at": "2026-09-29T13:26:06-10:00",
 "next_date": "2026-09-30",
 "next_sunrise": "06:11",
 "next_sunrise_iso": "2026-09-30T06:11"
}
```
2nd call same day: `refreshed false` (no network).

## 6. Uptime log (`System/scripts/uptime_log.py tick`, 13:26 HST)
- origin_start(inferred) logged; simulated 600 s gap in temp root → `heartbeat_gap` + `desk_up after_gap_s 600`.

## 7. MP4 converter (`Media/Video/scripts/mp4_converter.py`, 13:29 HST)
- synthetic 2 s sine MP3 + 320×240 PNG → h264 + aac, duration 2.000000 s, elapsed 0.22 s, RSS 92 MB; no-thumb path → ok false, rc 1. Test files in /tmp deleted.

## 8. jobs.py gates (import check, flags unset)
```
('geology_collect', False, 300)
('geology_kilauea_cams', False, 600)
('system_uptime_log', False, 60)
('voice_earthquake_report', False, [8])
('energy_sun_times', False, [])
```
All False with flags unset; True with the flag =1 in the environment. Running poller PID 105444 imported jobs.py at its start → nothing changes until the next poller start.

