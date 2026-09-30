# G3 — Database-root realignment (Energy + Plumbing) — 2026-09-29T10:58:45Z (2026-09-29 00:58 HST)

Canonical Database root: `/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database`. Backup of every edited file, the Database `.gitignore`, the BLE unit and the pre-restart BLE state: `/home/rootrecord/Database/GITHUB/g3-dbroot.bak-20260929-005646/`. Poller not restarted; `jobs.py`, `run-poller.sh`, the systemd unit and credentials unchanged. Existing data files were not moved or rewritten.

## 1. Skip check

`git log --since=1.hour -p --stat` in Pacific: Bruce Monitor Agent changed `Energy/lib/paths.py`, `System/lib/paths.py`, `Reports/scripts/worklog_lib.sh`, `Weather/scripts/sync-weather-database.sh`, `jobs.py`, `poller-watch.py` and the new log archive script. None of the target files had been fixed, so nothing was skipped.

## 2. Database .gitignore (privacy + churn)

The Database repo's `.gitignore` only covered binary media. Appended:

```text
/ENERGY/state/
/ENERGY/ports/
/GITHUB/plumbing/state/
*.pid
*.lock
```

`git check-ignore` confirms that `GITHUB/plumbing/state/holder.txt`, `ENERGY/state/ava-ecoflow-ble.pid`, `ENERGY/ports/solar-gate-state.json` and `*.lock` are ignored. No tracked file matched these rules, so nothing was untracked. This matters for privacy: the single-flight holder file records the full inference command, including the prompt. Target dirs were created where missing: `ENERGY/state`, `ENERGY/ports`, `GITHUB/plumbing/state`, `GITHUB/logs`, `Logs/Energy`.

## 3. Source changes (Pacific, env-default convention as in `worklog_lib.sh`)

```diff
# Pacific commit(s): 87a6469
+++ b/Energy/config/devices.conf
-energy_data=/home/rootrecord/Database/ENERGY
-samples=/home/rootrecord/Database/ENERGY/samples
-ports=/home/rootrecord/Database/ENERGY/ports
-soc=/home/rootrecord/Database/ENERGY/soc
-watts=/home/rootrecord/Database/ENERGY/watts
+energy_data=/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY
+samples=/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY/samples
+ports=/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY/ports
+soc=/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY/soc
+watts=/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY/watts
-ble_log=/home/rootrecord/Database/Logs/Energy/ava-ecoflow-ble.log
+ble_log=/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Logs/Energy/ava-ecoflow-ble.log
+++ b/Energy/scripts/actions/solar-gate-arm.sh
-STATE="/home/rootrecord/Database/ENERGY/ports/solar-gate-state.json"
+STATE="${SOLAR_GATE_STATE:-/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY/ports/solar-gate-state.json}"
+++ b/Energy/scripts/actions/solar-gate-disarm.sh
-STATE="/home/rootrecord/Database/ENERGY/ports/solar-gate-state.json"
+STATE="${SOLAR_GATE_STATE:-/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY/ports/solar-gate-state.json}"
+++ b/Energy/scripts/actions/solar-gate-status.sh
-STATE="/home/rootrecord/Database/ENERGY/ports/solar-gate-state.json"
+STATE="${SOLAR_GATE_STATE:-/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY/ports/solar-gate-state.json}"
+++ b/Energy/scripts/ble/ble-owner.py
-# Pacific copy (staged 2026-09-29): log/pid moved off the G2 skills tree to the Database; override via env.
-LOG = Path(os.environ.get("ENERGY_BLE_LOG", "/home/rootrecord/Database/Logs/Energy/ava-ecoflow-ble.log"))
-PID = Path(os.environ.get("ENERGY_BLE_PID", "/home/rootrecord/Database/ENERGY/state/ava-ecoflow-ble.pid"))
+# Pacific copy: log/pid live under the canonical RootRecord Database (2026-09-29); override via env.
+LOG = Path(os.environ.get("ENERGY_BLE_LOG", "/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Logs/Energy/ava-ecoflow-ble.log"))
+PID = Path(os.environ.get("ENERGY_BLE_PID", "/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY/state/ava-ecoflow-ble.pid"))
+++ b/System/scripts/plumbing/flm-warmup.sh
-LOG="/home/rootrecord/Database/GITHUB/logs/flm.log"
+LOG="${FLM_LOG:-/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/GITHUB/logs/flm.log}"
+++ b/System/scripts/plumbing/single-flight.sh
-STATE_DIR="${RR_PLUMBING_STATE:-/home/rootrecord/Database/GITHUB/plumbing/state}"
+STATE_DIR="${RR_PLUMBING_STATE:-/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/GITHUB/plumbing/state}"
```

`bash -n` and `py_compile` OK; modes unchanged (775, `ble-owner.py` 755). Nothing in the Pacific source reads the `devices.conf` `[paths]` keys (`read_runner.py` skips that section; Python uses `Energy/lib/paths.py`), so that edit is for consistency only. Still on G2 paths and not in scope: `[paths]` `log_dir`, `state_dir`, `skill_root`.

## 4. BLE owner — one controlled restart

The pid/log paths came from the script defaults (the unit sets no `Environment`), so a restart was needed. Stop, verify, start:

```text
pre: owners=1 MainPID=741782 oldpid=741782 newpid_exists=n
stop at 00:57:25 HST
after stop: active=inactive owners=0 oldpid_file=gone
start at 00:57:27 HST
after start: active=active MainPID=780266 owners=1 newpid=780266 oldpid_file=gone NRestarts=0
cmdline: /usr/bin/python3 <Pacific>/Energy/scripts/ble/ble-owner.py 
new log tail:
2026-09-29T00:57:27-10:00 owner: start pid=780266 interval_s=30.0 (thin owner; no pack scan)
2026-09-29T00:57:27-10:00 owner: heartbeat ok — atomic actions own short BLE sessions; poll buckets disabled
old log last line:
2026-09-29T00:57:25-10:00 owner: stop
journal:
2026-09-29T00:57:25-10:00 rootrecord-software-solutions systemd[2816]: Stopping ava-ecoflow-ble.service - RootRecord EcoFlow BLE Owner...
2026-09-29T00:57:25-10:00 rootrecord-software-solutions python3[741782]: 2026-09-29T00:57:25-10:00 owner: stop
2026-09-29T00:57:25-10:00 rootrecord-software-solutions systemd[2816]: Stopped ava-ecoflow-ble.service - RootRecord EcoFlow BLE Owner.
2026-09-29T00:57:27-10:00 rootrecord-software-solutions systemd[2816]: Started ava-ecoflow-ble.service - RootRecord EcoFlow BLE Owner.
2026-09-29T00:57:27-10:00 rootrecord-software-solutions python3[780266]: 2026-09-29T00:57:27-10:00 owner: start pid=780266 interval_s=30.0 (thin owner; no pack scan)
2026-09-29T00:57:27-10:00 rootrecord-software-solutions python3[780266]: 2026-09-29T00:57:27-10:00 owner: heartbeat ok — atomic actions own short BLE sessions; poll buckets disabled
RESULT=PASS
```

Owner downtime was about 2 s. The old pid file was removed by the old owner on stop. The old log's last line is `stop` at 00:57:25 HST; that old log is left in place.

## 5. Re-run checks (current runbook)

```text
power: ACAD online=1 BAT0 Charging 90%
--- solar-gate-status at 2026-09-29T10:57:45Z
WAITING
No data - solar gate state not written yet
rc=2
--- single-flight status before
IDLE
--- inference at 2026-09-29T10:57:46Z
during: status=BUSY job=ollama:qwen2.5:1.5b-instruct-q8_0:20260929-005746 p
during: holder file=holder.txt old-root holder=
[busy] refuse parallel run. holder: job=ollama:qwen2.5:1.5b-instruct-q8_0:20260929-005746 pid=780536 ts=2026-09-29T00:57:46-10:00 cmd=ollama run qwen2.5:1.5b-instruct-q8_0 [desk: none]
Reply in character only. If metrics are needed: say you cannot see the desk. Never invent watts/SOC/kWh. Never repeat these instructions.
User: Reply with exactly one word: ok
parallel rc=75
inference rc=0 finished 2026-09-29T10:57:49Z
stdout:
[ok] single-flight RUN ollama:qwen2.5:1.5b-instruct-q8_0:20260929-005746
ok

stderr (ANSI stripped, non-empty lines):
--- after: status=IDLE state dir listing: []
--- git: !! ENERGY/state/ 
```

(The `[busy]` line prints the holder, which includes the relay's standard persona preamble and the test prompt. No secrets.)

- **Plumbing non-NPU — PASS.** The warmup resolves through Pacific `System/scripts/plumbing/` (poller `ollama_warmup` 00:52:14 HST → `[ok] ollama up`). One inference went through the Pacific gate (rc 0, `ok`), and a parallel run was refused (rc 75). The holder was written and read under `/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/GITHUB/plumbing/state`, and nothing was written under the old root or `~/.ollama/skills/plumbing/state`. **NPU/FLM stays BLOCKED.**
- **Energy `solar-gate-status` (read-only) — PASS.** The Pacific script reads `/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY/ports/solar-gate-state.json` (= `paths.PORTS`) and returns the correct `WAITING` / no-data answer (rc 2); nothing is written. The actuating actions stay VERIFY PENDING.

## 6. G2 retirement

The dependency check covered the G2 tree, systemd user and system units, crontab, rc files, Pacific and processes. The only references left are self-headers, the dormant G2 `jobs.py` (+ `.bak`) and `SKILL.md`. Retired:

- `~/.ollama/skills/energy/scripts/actions/solar-gate-status.sh` → Pacific `Energy/scripts/actions/solar-gate-status.sh`
- `~/.ollama/skills/plumbing/scripts/ollama-warmup.sh` → Pacific `System/scripts/plumbing/ollama-warmup.sh`

Backup: `/home/rootrecord/Database/GITHUB/g2-retire.bak-20260929-005845/`. Not retired: G2 `single-flight.sh`, `run-ollama.sh` and `run-infer.sh` (the retained G2 Telegram relay `council-relay.py` / `relay.conf` point at them), `flm-warmup.sh` (NPU BLOCKED), `npu-status.sh` (no Pacific counterpart), and the arm/disarm and other actuating Energy actions.

## Open

- The poller log is still `/home/rootrecord/Database/Logs/Automations/automations_current.log`. Moving it needs a poller restart, so it was not moved.
- The BLE log `Logs/Energy/ava-ecoflow-ble.log` is now in the auto-synced Database repo. It isn't ignored (outside the minimal rule set) and gains a heartbeat every 30 s, so decide whether to ignore or archive it like the Automations log.
- `Github/scripts/common.sh` `DATABASE_ROOT` still defaults to `/home/rootrecord/Database` (backup/intake root; not in this item).
- **Poller reads the old root (stale desk status since ~00:42 HST):** `rootserver_poller.py` has `ENERGY_ROOT` defaulting to `/home/rootrecord/Database/ENERGY` and `SYSTEM_STATUS_JSON` pointing at the old root. Its 00:59:31 HST line reads `ENERGY status=live B2=100% B1=0% … src=/home/rootrecord/Database/ENERGY`, while the same cycle's fresh read says `SUMMARY=delta2 soc=89%`. The old-root `soc/*-last.json` stopped updating at 00:41 / 00:42 HST, and the canonical ones are current. Fixing this needs a poller change plus a restart: not done, recorded as open.
