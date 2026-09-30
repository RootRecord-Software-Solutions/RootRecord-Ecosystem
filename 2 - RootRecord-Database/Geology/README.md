# Geology — Database byte home

Written by Pacific `Geology/` (code owner). Added 2026-09-29 (migration-geology). State: **LANDED**, collector PASS on one manual run; scheduled jobs gated OFF.

```text
Geology/
  collector-last.json                 per-source ok / error / ms of the last collector run
  Earthquakes/
    hawaii-last.json                  USGS FDSN, Hawaiʻi bbox 18.5–22.5 N / −160.5…−154.5 E, M≥1.0, last 24 h
    global-last.json                  USGS summary feed 2.5_day (Hawaiʻi ids removed)
    Daily/hawaii-YYYYMMDD.jsonl       append-only first-seen log (HST date), dedupe by USGS id vs today + yesterday
    Daily/global-YYYYMMDD.jsonl
    quakes.db                         on-demand backfill SQLite (git-ignored)
  Volcanoes/
    hvo-last.json                     all HVO-monitored volcanoes: alert level + aviation color code (HANS)
    kilauea-last.json                 Kīlauea (vnum 332010): level, color, latest notice + VAN/VONA synopsis, headline, erupting, multiplier
    mauna-loa-last.json               Mauna Loa (vnum 332020): same fields
    Daily/hvo-notices-YYYYMMDD.jsonl  append-only HVO notices (HANS getNewestOrRecent), dedupe by notice id
    Cams/cams-last.json               USGS V1/V2/V3 cam catalog + still status
    Cams/v{1,2,3}cam-last.jpg         latest USGS stills (git-ignored *.jpg)
```

| Writer (Pacific) | Job id (`Automations/scripts/jobs.py`) | Every | Gate (read at poller start) |
| --- | --- | --- | --- |
| `Geology/scripts/geology_collect.py all` | `geology_collect` | 300 s | `RR_GEOLOGY=1` |
| `Geology/scripts/kilauea_cams.py` | `geology_kilauea_cams` | 600 s | `RR_KILAUEA_CAMS=1` |
| `Geology/scripts/earthquakes_backfill.py` | — (on demand) | — | — |

Readers: Pacific `Media/Voice/scripts/voice_reports.py earthquake_report` (job `voice_earthquake_report`, gate `RR_VOICE_QUAKE=1`) and `kilauea_report` (job `voice_kilauea_report`, gate `RR_VOICE_KILAUEA=1`); no delivery. Keeping these jobs.py registrations is a sign-off item (`Logs/Migration/migration-jobs-py-additions-20260929.md`).

Event fields (since 2026-09-29 13:49 HST): every quake event also carries `nearest` = {location_id, name, country_code, admin1_code, km} — the closest place in Pacific `Geology/config/global-locations.json` within 250 km (G0 global poller rule), else `null`.

Rules: timestamps ISO 8601 `-10:00` (USGS times kept as `time_utc` + `time_hst`); a failed source never overwrites its last good file; public no-key sources only; measured values only.
