# G3 poller realign evidence — 20260929T111731Z

No secrets in this file. Times HST (UTC-10). Executor: desk agent for Alexander Storey.

## Backups
- Poller/unit/common.sh/Database .gitignore: `/home/rootrecord/Database/GITHUB/g3-poller.bak-20260929-011046/`
- G2 retirement: `/home/rootrecord/Database/GITHUB/g2-retire.bak-20260929-011731/`

## Task 1 — poller paths + one restart — **PASS**
- Before: `ENERGY ... B2=100% ... src=/home/rootrecord/Database/ENERGY` (old-root files last written 00:42:46; fresh new-root delta2 SOC 85–89%).
- Edits (Pacific `d9f074b`, auto-sync 01:11:16):
  - `Automations/scripts/rootserver_poller.py`: `ENERGY_ROOT` default → `2 - RootRecord-Database/ENERGY` (env override kept); `SYSTEM_STATUS_JSON` → `2 - RootRecord-Database/SYSTEM/status/system-status.json` (new env override `SYSTEM_STATUS_JSON`).
  - `Automations/scripts/poller/run-poller.sh`, `open-poller-window.sh`: `POLLER_LOG` default → `2 - RootRecord-Database/Logs/Automations/automations_current.log` (matches `poller-watch.py` + `archive_automations_log_hourly.sh`).
  - `~/.config/systemd/user/rr-rootserver-poller.service` `Environment="POLLER_LOG=…new root…"` and `.service.d/logging.conf` StandardOutput/StandardError `append:` → same new-root file (not in git).
- Checks: `py_compile` OK, `bash -n` OK, `daemon-reload` OK.
- Restart: one `systemctl --user restart rr-rootserver-poller.service` at 01:11:28. Old PID 770787 ignored SIGTERM → SIGKILL at 30 s TimeoutStopSec (pre-existing slow stop). New MainPID **804007** active 01:11:59, NRestarts=0, single python poller + one cloudflared (804021), tunnel 4 conns.
- After: `01:12:04 ENERGY status=live B2=85% ... src=/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/ENERGY`; `ENERGY/soc/delta2-last.json` soc=85.0 @01:12:05 → match. `GET 127.0.0.1:8799/system-status.json` == new-root file (generated_at 2026-09-29T11:12:06.180Z) → match.
- Old-root log `/home/rootrecord/Database/Logs/Automations/automations_current.log` last write 01:11:41 (no longer written).
- Git: `Logs/Automations/automations_current.log` is **tracked** in Database → every sync commit now includes it (10/10 commits 01:12–01:18). Ignore rule added (see Task 2) is inert until untracked.

## Task 2 — BLE heartbeat log ignore — **VERIFY PENDING (BLOCKED on untrack)**
- Database `.gitignore` (commit `a05805a`, 01:13:41) adds `/Logs/Energy/ava-ecoflow-ble.log` and `/Logs/Automations/automations_current.log`.
- `git check-ignore --no-index` matches both (lines 51/52); normal `git check-ignore` = not ignored because both are already tracked.
- Needs owner-approved `git rm --cached Logs/Energy/ava-ecoflow-ble.log Logs/Automations/automations_current.log` (git write — NOT done). Note `Logs/Automations/README.md` says automation logs "may be synchronized" — owner to confirm intent.

## Task 3 — Github/scripts/common.sh — **LANDED**
- Pacific `75d86f2` (01:14:15): `DATABASE_ROOT` default → new root (env override kept). `BAK_ROOT` default pinned to `/home/rootrecord/Database/GITHUB` (flags/worktrees/logs must stay outside the auto-synced Database git tree and match `stack/{do,schedule}-stack-reload.sh`). `INTAKE_ROOT` follows DATABASE_ROOT (only mkdir'd by `ensure_bak_root`).
- Sourced by: bak-new.sh, setup-remote.sh, setup-all-remotes.sh, sync-all.sh, push-repo-once.sh. `bash -n` OK. Sync scripts not run manually.

## Task 4 — Telegram relay — **PASS (poll/auth); replies BLOCKED (models)**
- Relay reads `relay.conf` SECRETS_1=`~/.config/ava-council/secrets.env` (absent) and SECRETS_2=`~/master/master-key.env` (mtime 2026-09-29 01:07:50 HST). Names present + non-empty in SECRETS_2: TELEGRAM_AVA_TOKEN, TELEGRAM_BRUCE_TOKEN, TELEGRAM_CARLY_TOKEN.
- Relay PID **804326** (Pacific council-relay.py) started 01:11:59 by poller boot job `council_relay` → ensure-relay.sh (relay lives in poller cgroup, so the poller restart restarted it; env picked up fresh). Single instance; alive 4m06s at 01:16:06; no 401/Unauthorized/Traceback in `/home/rootrecord/Database/GITHUB/logs/council-relay.log` (an HTTP 401 would raise and kill the process). Previous log lines were "No data: poll token".
- `ollama list`: no ava-telegram / bruce-telegram / carly-telegram (and no ava/bruce/carly fallbacks) → reply path BLOCKED.
- G2 retirement (zero live refs in Pacific/Database/Library code, ~/.config/systemd, crontab, running processes): **RETIRED** `~/.ollama/skills/coms/telegram/scripts/{council-relay.py,ensure-relay.sh}`, `~/.ollama/skills/plumbing/scripts/{run-ollama.sh,run-infer.sh}` (skills `8297c26`, 01:17:54). MIGRATED.md written/updated; SKILL.md kept. **Kept** `single-flight.sh` (G2 `npu-status.sh` still calls it).

## Addendum 01:21 HST — unplanned stack reload + relay fix
- 01:19:00 auto-sync pulled Bruce Monitor Agent Pacific `480990b` (+`afb97d4`, `2dc3186`: Telegram log/state → canonical root) → `do-stack-reload` restarted the poller 01:19:09–01:19:25 (not done by this executor). New MainPID **817736**, NRestarts 0, B2=85% still matches.
- Reload killed relay 804326 (it lives in the poller cgroup); boot job `council_relay` then FAILED code=127: `ensure-relay.sh` line 12 `LOG=` path with spaces unquoted.
- Fix: quoted that one value (backup `/home/rootrecord/Database/GITHUB/g3-relayfix.bak-20260929-012052/`), `bash -n` OK, ran `ensure-relay.sh` once 01:20:52 → relay PID **821015**; alive 47 s at 01:21:39, single instance, 0 × 401/Unauthorized/Traceback in the new log `2 - RootRecord-Database/Logs/Communications/council-relay.log`. Pacific commit `f27604d`.
- Caveat: 821015 was started from the desk agent session (cgroup `app-grok-bot-*.scope`), not the poller unit; the next poller boot job will own it. The new relay log is also tracked in the Database repo.
- **01:25:30 HST — owner-approved untrack, PASS:** `git rm --cached` of `Logs/Energy/ava-ecoflow-ble.log`, `Logs/Automations/automations_current.log`, `Logs/Communications/council-relay.log` (+ `.gitignore` line 53 for council-relay.log); index deletions in auto-sync `eabe62e` (01:25:42); files remain on disk (BLE and poller logs still growing); `git check-ignore` matches all three; next syncs `132e293`, `bf32153` contain none of them. README note: `Logs/Automations/README.md`.
