# G3 Evidence — Energy status, Plumbing single-flight, B1 reading — 2026-09-29T10:46:18Z (2026-09-29 00:46:18 HST)

| Item | State | Evidence |
| --- | --- | --- |
| Energy `solar-gate-status` (read-only) | PASS | Script only `cat`s `/home/rootrecord/Database/ENERGY/ports/solar-gate-state.json` (no BLE, no writes; identical logic to G2). Run once 2026-09-29T10:43:09Z from Pacific `Energy/scripts/actions/` → `WAITING` / `No data - solar gate state not written yet`, exit 2 (the designed honest no-data answer; the gate has never been armed). |
| Energy actuating actions (arm/disarm, AC always-on on/off) | VERIFY PENDING | They change hardware state via BLE; not run (no approved actuation). |
| Plumbing non-NPU single-flight | PASS (after mode fix) | First try 10:44:24Z failed: `run-ollama.sh` → `single-flight.sh: Permission denied` (exit 126) — Pacific `run-infer.sh`, `run-ollama.sh`, `single-flight.sh` were mode 644 (G2 copies are 755). Restored 775 (git mode 100644 → 100755). Retry 10:45:00Z: `run-ollama.sh qwen2.5:1.5b-instruct-q8_0 "Reply with exactly one word: ok"` → `[ok] single-flight RUN …` / `Ok`, exit 0 in ~4 s; a parallel `single-flight.sh run` during it was refused (`[busy]`, exit 75); holder written then cleared under `/home/rootrecord/Database/GITHUB/plumbing/state`; lock `/run/user/1000/rootrecord-inference.lock`. |
| Plumbing NPU (FLM) | BLOCKED | unchanged: no FLM binary / service; `:52625` unreachable. |
| B1 = River 2 Pro reading 0% | Finding only | See below. |

## B1 (River 2 Pro) = 0% — read-only finding

- B1 is `river2pro` (River 2 Pro, role `laptop_ac_car_dc`). Its readings come from the EcoFlow cloud API (`source: api` in every sample and in `Database/ENERGY/soc/river2pro-last.json`). BLE is not a data source: the BLE owner is a heartbeat-only process, and no BLE action ran tonight.
- From 23:40 to 00:19 HST the API returned the same values 24 times (`soc=26% ac_out=73W usbc=56W`). After a gap in river2pro reads (00:19:17 → 00:24:37 HST), every read (12 so far) is `soc=0% ac_out=73W usbc=95W`.
- At about 129–168 W of reported output, 26% of a River 2 Pro should last well over an hour, and the values stayed identical for 40 minutes. So the API snapshots update rarely: the drop to 0% is either a late report of a real deep discharge or an API/device reporting artifact. From the desk it cannot be told apart; check the River 2 Pro display.
- Not caused by the migration: it started at 00:24:37 HST, before both cutovers (00:33 / 00:35 HST), and the data path is the API. The desk itself is on mains (`ACAD online=1`; laptop battery 83%, charging).

```text
--- solar-gate-status
WAITING
No data - solar gate state not written yet
rc=2
--- plumbing modes now
775 plumbing/flm-warmup.sh
775 plumbing/ollama-warmup.sh
775 plumbing/run-infer.sh
775 plumbing/run-ollama.sh
775 plumbing/single-flight.sh
--- single-flight test output (10:45:00Z)
[ok] single-flight RUN ollama:qwen2.5:1.5b-instruct-q8_0:20260929-004500
Ok

rc=0
--- single-flight status now
IDLE
--- river2pro SUMMARY value counts
23:40–00:24:
     24 soc=26% solar=0W ac_out=73W usbc=56W
since 00:24:
     12 soc=0% solar=0W ac_out=73W usbc=95W
--- river2pro samples 00:18–00:25
read-river2pro-20260929-001839.json
read-river2pro-20260929-001917.json
read-river2pro-20260929-002437.json
--- host power
ACAD: status= online=1 capacity=
BAT0: status=Charging online= capacity=84
```

## Correction — 2026-09-29T10:49:33Z (00:49 HST)

At 00:46:45 HST another editor revised the G3 runbook (Library `f1109fc`). The active Database authority is now `/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database` (Pacific commits `64cfd08`, `2287a77`, `1f0efae`, `d081968` and `2c5b3d2`, 00:42 HST). Re-scored against the revised criteria:

- **Plumbing non-NPU → FAIL on the state-path criterion.** The gate itself worked: one inference went through, and the parallel run was refused. But `System/scripts/plumbing/single-flight.sh` still defaults `STATE_DIR` to the old `/home/rootrecord/Database/GITHUB/plumbing/state`. The runbook now requires `…/2 - RootRecord-Database/GITHUB/plumbing/state`, which does not exist. The exec-bit fix stands.
- **Energy `solar-gate-status` → VERIFY PENDING.** It runs read-only, but `solar-gate-status.sh`, `solar-gate-arm.sh` and `solar-gate-disarm.sh` hardcode the old `/home/rootrecord/Database/ENERGY/ports/solar-gate-state.json`, while `Energy/lib/paths.py` `PORTS` now points at the canonical root.
- **G2 retirement reverted.** Because neither row now passes, `~/.ollama/skills/plumbing/scripts/ollama-warmup.sh` and `~/.ollama/skills/energy/scripts/actions/solar-gate-status.sh` were restored byte-identical from `/home/rootrecord/Database/GITHUB/g2-retire.bak-20260929-004619/`, and the two `MIGRATED.md` files were removed.
- Other old-root references that still point at `/home/rootrecord/Database` (recorded, not changed): `flm-warmup.sh` LOG; `ble-owner.py` LOG/PID defaults and `devices.conf` `ble_log`; the running poller still writes `/home/rootrecord/Database/Logs/Automations/automations_current.log` (the canonical-root path in runbook §5 has no such file; the poller was not restarted).
- Side effect to settle before moving the state: the Database repo is auto-synced and `.gitignore` covers only binary media. So single-flight holder files and `solar-gate-state.json` at the canonical root would be committed.
- B1 finding unchanged. The canonical `ENERGY/soc/river2pro-last.json` still shows `soc: 0.0`, `source: api`.
