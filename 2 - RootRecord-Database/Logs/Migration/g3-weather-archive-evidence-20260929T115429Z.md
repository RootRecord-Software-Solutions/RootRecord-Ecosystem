# G3 weather hook-in + old-root archive evidence — 2026-09-29 ~01:54 HST (11:54Z)

No secrets. Backup of every edited file: `/home/rootrecord/Database/GITHUB/g3-archive.bak-20260929-014552/` (docs under `docs/`).

| Item | State | Evidence |
| --- | --- | --- |
| A. Old-root data archive | **PASS** | `lsof` 0 open files; moved 01:46 HST to `2 - RootRecord-Database/Archive/Previous-Datasets/G2-old-root-20260929/` (4.5 GB: A-EYES, ENERGY, Energy, Logs, ROOTRECORD, SYSTEM, WEATHER, WORKLOG, intake). Kept in place: `/home/rootrecord/Database/GITHUB/`, `/home/rootrecord/Database/README.md`. Database `.gitignore` + README-only tracking in `5377389`, inventory README `45b040c`; no bulk file in any sync commit since (checked 12 commits). |
| B. `Energy/db/store.py` | **LANDED** | Pacific `abc78b2`: `ROOTRECORD_DB` / `ROOTRECORD_LAYERS_DIR` env overrides, defaults → canonical `ROOTRECORD/`. Copy of `rootrecord.db` (byte-identical) + `layers/` placed at canonical `ROOTRECORD/` (281M, git-ignored). No restart. |
| C. `push-repo-once.sh` | **LANDED** | Pacific `abc78b2`: `is_runtime_code_tree` no longer matches `skills` / `~/.ollama/skills` → G2 pulls no longer arm a poller stack reload. |
| D. Weather | **PASS** (reports VERIFY PENDING) | G2 weather code copied to Pacific `Weather/` (G2 kept) `b72db19`; Pacific venv `Weather/.venv` (git-ignored, from new `Weather/requirements.txt` = G2 venv freeze); `run_poller.py` data → canonical `WEATHER/Hawai'i/` (env `WEATHER_DATA_ROOT`); ensure script → Pacific venv + canonical log, match pattern `[Ww]eather/…` (no parallel owners) `e977252`; `jobs.py` `weather_poller` enabled → Pacific script `e977252`; `sync-weather-database.sh` guard: skips unless canonical `WEATHER/` is its own git repo. One poller restart 01:49:36 → MainPID 880218, NRestarts 0; boot job started weather PID 880724 (single). By 01:53: 110 files / 207M under canonical `WEATHER/Hawai'i/`, 0 tracebacks; 8 upstream resource warnings (HTTP 500 / HTML placeholder, same class as the archived G2 log). `WEATHER/Hawai'i/reports/` not generated yet (report tier cadence). |
| E. Energy vendor | **LANDED** | Pacific `Energy/lib/vendor/` = G2 copy (102 files, only README differs); README now names Pacific canonical `99cc71e`. G2 copy kept. |
| F. READMEs | **LANDED** | Automations, Energy, Energy/db/SKILL, Reports, System, Geology, Telegram, Weather READMEs → canonical root (`99cc71e`, `662bf97`). |

Restart side effects: in-flight `github_sync_all` killed (code -15, no index.lock left); relay 821015 (desk session) is gone and the boot job started relay **880530** under the poller unit (single); cam_server re-started (single).

Open: dormant G2 code (27 files) still names the moved old-root paths; `WEATHER/` is not yet its own RootRecord-Weather-Database git repo, so weather data is local only; weather first pass grows disk fast (~200 MB in 4 min).

- **Relay crash finding (01:57 HST):** relay 821015 died 01:39:21 HST on an unhandled `TimeoutError` in getUpdates (network read timeout, not auth); relay was down until the 01:50 boot job. Fix: `council-relay.py` now retries on URLError/TimeoutError/OSError (Pacific `b3754fb`; takes effect at the next relay start, running PID 880530 not restarted).
