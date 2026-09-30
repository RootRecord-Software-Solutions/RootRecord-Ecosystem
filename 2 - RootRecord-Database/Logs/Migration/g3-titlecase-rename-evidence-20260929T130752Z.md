# Database top-level Title-case rename — plan + evidence (2026-09-29, started 03:10 HST)

Requested by Alexander (03:03 HST): "use new format for everything… folders nice and clean". This is one approved stack stop/start. No symlinks, no shims, no data deleted. `/home/rootrecord/Database/GITHUB` (old-root BAK_ROOT) and `Archive/` are untouched.

## 1. Inventory and rename map (new root `2 - RootRecord-Database/`)

| Old | New | Size / files | Git | Collision | Resolution |
| --- | --- | --- | --- | --- | --- |
| `ENERGY/` | `Energy/` | 940K / 223 | 222 tracked (samples, soc, watts); `state/`, `ports/` ignored | `Energy/` exists: only an empty `state/` dir (placeholder, 0 files) | `rmdir` the empty placeholder, then `mv` |
| `SYSTEM/` | `System/` | 3.5M / 217 | 217 tracked | `System/` empty placeholder | `rmdir`, `mv` |
| `WEATHER/` | `Weather/` | 390M / 1317 | ignored | `Weather/` empty placeholder | `rmdir`, `mv`; ignore rule → `/Weather/` |
| `GITHUB/` | `Github/` | 20K / 1 (`logs/flm.log`, `plumbing/state/`) | ignored | `Github/` empty placeholder | `rmdir`, `mv`; rules → `/Github/…` |
| `ROOTRECORD/` | `RootRecord/` | 281M / 10 (energy sqlite + layers) | ignored | none | `mv`; rule → `/RootRecord/` |
| `WORKLOG/` | `Worklog/` | 1.6M / 9 | 9 tracked | none | `mv` (mode 700 kept) |
| `intake/` | `Intake/` | 0 files (`council-relay/` state dir) | — | none | `mv` |

Kept as-is: `AI/` (acronym, Alexander's own layout), `Archive/`, `Geology/`, `Logs/`, `Media/`, `Users/`.
- `Logs/Energy`, `Logs/System`, `Logs/Weather` and `Logs/Github` are log subtrees under `Logs/`, so they don't collide with the top-level data folders.
- The top-level `Energy/`, `System/`, `Weather/` and `Github/` placeholders (Alexander, 20:04 HST) are the intended targets.
- No tracked file sits under an ignored path, so no `git rm --cached` is needed.

## 2. References to update
- **Pacific code:**
  - `Automations/scripts/rootserver_poller.py` (SYSTEM_STATUS_JSON, ENERGY_ROOT), `poller/poller-watch.py` (WEATHER report path only), `poller/poller-dashboard.py` (ENERGY), `jobs.py` (comments lines 6–7 only).
  - `Communications/telegram/scripts/council-relay.py` (STATE_DIR default), `telegram/config/relay.conf` (DESK_LIVE_FILE, STATE_DIR), `Github/scripts/common.sh` (INTAKE_ROOT).
  - `Energy/lib/paths.py`, `Energy/db/store.py`, `Energy/config/devices.conf`, `Energy/scripts/ble/ble-owner.py` (PID), `Energy/scripts/actions/solar-gate-{arm,status,disarm}.sh`.
  - `System/lib/paths.py`, `System/scripts/plumbing/single-flight.sh`.
  - `Reports/scripts/daily_roll_up.sh`, `worklog_lib.sh` (default + exclusion globs; old-root lines untouched).
  - `Weather/scripts/{run_poller.py,ensure-weather-poller.sh,sync-weather-database.sh,debug_fetch_pass.py}`, `Weather/config/{resources,hosts}.yaml` (comments), `Weather/scheduler/run_cycle.py`, `Weather/hurricanes/scripts/sources.py` (docstrings).
- **Database:** `.gitignore` rules `/ENERGY/state/`, `/ENERGY/ports/`, `/GITHUB/plumbing/state/`, `/WEATHER/`, `/ROOTRECORD/`, `/GITHUB/logs/`, plus `README.md` layout table.
- **Pacific READMEs:** Energy, Energy/db/SKILL.md, Weather, Reports, System.
- **Library:** WO-SRV, WO-ECO, runbook, checklist, retirement table (live references only; historical evidence files keep the old names).
- **No references in:** systemd units and env, autostart, Website, Node, US-Mainland, G2 skills, Master-Prompt.

## 3. Procedure
Edits are staged and syntax-checked first, then applied in one window:
1. `stop-poller-stack.sh` (poller, relay, weather, cam, cloudflared, globe).
2. `rmdir` the empty placeholders and `mv` the 7 folders.
3. Install the staged files, including `.gitignore`, before auto-sync can run again.
4. `systemctl --user start rr-rootserver-poller` (boot jobs bring back relay, still quiet, plus weather, cam, globe and the tunnel).
5. Relaunch the read-only dashboard only.

BLE owner (`ava-ecoflow-ble`, separate unit) is not restarted: it writes its PID only at start and unlinks it with `missing_ok`. Rollback: move the folders back, restore the backups, one restart.

## 4. Result (03:24 HST)
- **Rename:** the stack stopped at 03:09:36 (the first stop at 03:08:56 was interrupted; the old poller was SIGKILLed at TimeoutStopSec). The 7 moves were done at 03:09:39, 27 staged files were installed, and the poller started at 03:09:39 (PID 94145).
- **OOM restart loop:** at each boot, `flm_npu_warmup` → `flm serve llama3.2:3b` (about 10 GB) OOM-killed the unit (NRestarts 11). Warmup was made opt-in at 03:13 (Pacific `ff298b2`). The poller has been stable since 03:13:28 (PID 105444).
- **Services (single instance):** relay 105964 (replies OFF), cam 106033, weather 106159, cloudflared 105450, globe 94778, BLE owner 3195 (not restarted), dashboard 111257 (relaunched 03:16:34).
- **Git:** Database `92bd69c` has 455 renames (`R`). `git ls-files` shows 0 paths under the old names. check-ignore holds `/Weather/`, `/RootRecord/`, `/Github/logs/`, `/Energy/state/`, `/Energy/ports/`. No `git rm --cached` was needed. Pacific code: `1368822`.
- **Fresh data (new names):** `Energy/soc` 03:21–03:22 (BLE), `System/samples/sys-20260929-032425.json`, 147 Weather files written in the last 3 min, `Worklog/worklog_current.md` 03:18:48.
- **Old names:** none recreated at 03:15 or at 03:24.
- **Docs:** Pacific READMEs (Energy, Energy/db/SKILL, Weather, Reports, System), Database README, and Library WO-SRV/retirement table/checklist/runbook were updated (new-root path forms only; old-root and archive paths unchanged). Backup: `/home/rootrecord/Database/GITHUB/g3-titlecase-docs.bak-20260929-032352/`.
