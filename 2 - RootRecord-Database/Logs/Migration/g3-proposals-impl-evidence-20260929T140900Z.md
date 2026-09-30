# G3 proposals implementation — evidence (2026-09-29 ~03:55–04:10 HST)

Scope: four Library `08-ideas` proposals approved by Alexander: (1) weather/relay auto-recovery, (2) Pacific `npu-status.sh`, (3) relay quiet-mode inbox + replay, (4) weather retention policy (the Weather repo is NOT approved and was skipped).
Rules followed: no git write commands (auto-sync committed), no restarts, no sudo, no hardware, no Telegram sends, no deletions, no resident models.
Backup: `/home/rootrecord/Database/GITHUB/g3-proposals-impl.bak-20260929-035910/` (originals of every modified file).

## Commits (auto-sync)

| Repo | SHA (HST) | Files |
| --- | --- | --- |
| Pacific | `5353e1f` (04:02:54) | `Automations/scripts/supervise-services.sh` (new), `Communications/telegram/scripts/council-relay.py`, `Communications/telegram/scripts/relay-inbox-replay.py` (new), `System/scripts/plumbing/npu-status.sh` (new) |
| Pacific | `52573e7` (04:06:53) | `Automations/scripts/jobs.py` (+22 lines, 2 jobs), `Communications/telegram/README.md`, `Weather/README.md`, `Weather/scripts/weather-retention.py` (new) |
| Database | `57172d0` (04:03:03) | `.gitignore` (Relay-Inbox rule), `Logs/Communications/Relay-Inbox/README.md` |
| Database | `a775c2f` (04:07:02) | `Logs/Weather/Retention/weather-retention_dry-run_2026-09-29_0404.md` |

(The same sync commits also carry the concurrent Voice/AI agent's files.)

## 1. Supervisor (03:59:54 HST) — detection only, no process killed

```text
$ supervise-services.sh --dry-run
[supervisor] weather alive pid=106159 (dry-run)
[supervisor] relay alive pid=105964 (dry-run)
# live backoff state dir not created in dry-run
$ supervise-services.sh --dry-run --pretend-dead weather --state-dir <tmp>   (x5)
weather DEAD — WOULD-RESPAWN via ensure-weather-poller.sh (attempt 1/3 in window)
weather DEAD — WOULD-RESPAWN ... (attempt 2/3) / (attempt 3/3)
weather DEAD — WOULD-BLOCK (3 restarts in last 1800s >= 3)
weather DEAD — BLOCKED since 2026-09-29T03:59:54-10:00 (no retry ...)
$ ... --pretend-dead relay   -> relay DEAD — WOULD-RESPAWN via ensure-relay.sh (attempt 1/3 in window)
$ ... (alive again)          -> weather seen alive — BLOCKED cleared
$ supervise-services.sh --pretend-dead weather   -> refused (only with --dry-run), rc=2
```
Load 1.87/1.51/1.41, MemAvailable 7399 MB. Temp state dir removed.

## 2. npu-status.sh (04:00:26 HST) — read-only, idle

`/dev/accel/accel0` present; packages libxrt-npu2, libxrt-utils, libxrt-utils-npu, libxrt2 2.25.0, linux-firmware-amd-misc; single-flight `IDLE`; `IDLE (on demand): no flm serve, :52625 closed — normal`; rc=0. G2 original unchanged (317 B, 2026-09-26 14:23).

## 3. Relay inbox (04:02:14 HST) — parse test, synthetic data in a temp dir, api() tripwired

- 4 synthetic holds → targets `bruce`, `pipeline:ava>bruce>carly>ava`, `ava` (private), `ava` (default); record keys: chat_id, chat_type, from, message_id, persona_target, received_ts, status, text, ts, update_id.
- Seeded earlier-hour record rotated to `Archive/2026-09-29/relay-inbox_2026-09-29_0300.jsonl`; files mode 0600.
- Replay list: records=5 pending=5 unparsed=0; `--json` OK; `--send` without `RR_RELAY_REPLIES=1` and with `=0` → refused, rc=3, no ledger written; api() never called.
- Real inbox (not created yet): records=0.
- `git check-ignore -v --no-index`: `relay-inbox_current.jsonl`, `Archive/…/*.jsonl`, `replayed.jsonl` ignored by `.gitignore:89`; `README.md` re-included by `.gitignore:90`.

## 4. Weather retention (04:04:41 HST) — dry run on real data

Report: `Logs/Weather/Retention/weather-retention_dry-run_2026-09-29_0404.md`. Would move 0 files / 0 bytes, would delete 0. Kept: current 282 files / 232,091,347 B; text_dated 255 / 14,876,501 B; imagery_dated 139 / 244,386,793 B; daily_zip 0; reports_archived 1,632 / 28,208,173 B; hurricanes 1 / 1,697 B; other 2 / 1,310,196 B. Weather/ total 2,313 files / 521,137,050 B (497.0 MB). Disk free 201.3 GB. Log 30,192 B (no rotation). Alarms none. Oldest dated item 2026-09-29. Peak RSS 17.8 MB, 0.08 s; load 1.54, MemAvailable ~11.8 GB.
Synthetic tree in /tmp (before the real run): dry run flagged 4 old items + an 11 MB log; `--apply` moved them to `Archive/Weather-<YYYYMM>/` with a README each (0 deleted), rotated the log by copytruncate; a second dry run → 0. Temp tree removed.

## Activates at the next poller start
`service_supervisor` (EVERY_SECONDS 300 s) and the relay inbox. The relay (PID 105964) and weather (PID 106159) run inside the `rr-rootserver-poller.service` cgroup with `KillMode=control-group` (checked read-only 04:09), so a poller stop/start restarts the relay via the ON_BOOT `council_relay` job and it loads the new code then. `weather_retention` stays `enabled: False` and `--dry-run`.
