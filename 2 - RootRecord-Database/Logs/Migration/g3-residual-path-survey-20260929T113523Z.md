# G3 residual path survey — 2026-09-29 01:35 HST (11:35Z)

Read-only survey of references to the old root `/home/rootrecord/Database` and to `~/.ollama/skills` in the Pacific repo and the Database repo (plus user systemd units / crontab). Excludes `.git`, `node_modules`, `.venv`, `__pycache__`. No secrets. Nothing deleted.

**Standing rule (Alexander, 2026-09-29):** never retire or delete G2/legacy code; "no live references" is not grounds (unimported automations, e.g. `rootrecordsoftwaresolutions/old`, may need it). Retirement only with Alexander's explicit sign-off.

Classes: **MM** must-migrate · **KI** keep-intentionally · **ND** needs-decision · **FX** fixed in this pass (backup `/home/rootrecord/Database/GITHUB/g3-defaults.bak-20260929-013505/`).

## Fixed this pass (no restart; effect at next natural run/reload)
| File | Change | Takes effect |
| --- | --- | --- |
| `Energy/config/devices.conf` | `log_dir` → new-root `Logs/Energy`; `state_dir` → new-root `ENERGY/state`; `skill_root` → Pacific `Energy` (comment added) | No code reads `[paths]` (`read_runner.py` skips it; G2 energy code uses its own G2 `devices.conf`) → no behaviour change |
| `Automations/scripts/stack/do-stack-reload.sh`, `schedule-stack-reload.sh` | `STACK_RELOAD_LOG` default → new-root `Logs/Automations/stack_reload_current.log` (matches Database `Logs/Automations/README.md`) | next stack reload; `BAK_ROOT` left on old root on purpose |
| `Reports/scripts/daily_roll_up.sh` | `WORKLOG_DIR` default → new-root `WORKLOG` (writer `worklog_lib.sh` already there; old-root WORKLOG is stale) | next `reports_daily_roll_up` run 18:30 HST |

## Pacific — old root `/home/rootrecord/Database`
| # | Ref | Class | Note |
| --- | --- | --- | --- |
| 1 | `Energy/db/store.py:14,44` `DEFAULT_DB_PATH`/`LAYERS_DIR` → `/home/rootrecord/Database/ROOTRECORD/…` | **ND** | 3.6 MB `rootrecord.db` last written 09-28 16:18; no `ROOTRECORD/` in the new root. Needs a data-location decision (copy/move DB, or retire the sqlite path). The poller's `from energy.db.latest` import fails anyway (package is `Energy`), so the poller uses JSON. |
| 2 | `Github/scripts/common.sh:15`, `stack/do-stack-reload.sh:21`, `stack/schedule-stack-reload.sh:12` `BAK_ROOT=/home/rootrecord/Database/GITHUB` | **KI** | flags/worktrees/backups must stay outside the auto-synced Database git tree |
| 3 | `Weather/scripts/ensure-weather-poller.sh:28` `LOG_DIR` old root | **KI** | `weather_poller` job disabled; Weather WO |
| 4 | `System/scripts/plumbing/run-ollama.sh:10`, `run-infer.sh:7`, `Github/scripts/push-repo-once.sh(.pre-pull):6`, `Communications/telegram/SKILL.md:14` "Bak:" comments | **KI** | comments; backups stay at old-root GITHUB |
| 5 | Docs: `Automations/README.md:14,15,42`, `Energy/README.md:15`, `Reports/README.md:5,47`, `System/README.md:13`, `Github/README.md:16` (KI), `Energy/db/SKILL.md:30` (ND with #1), `Geology/README.md:14` | **MM (docs)** | doc drift only; say old root is the log/data authority |
| 6 | `*.bak-2026092*` (devices.conf ×2, store.py, System paths.py), `ava-ecoflow-ble.service.proposed` | **KI** | historical backups/proposals |

## Pacific — `~/.ollama/skills`
| # | Ref | Class | Note |
| --- | --- | --- | --- |
| 7 | `Automations/scripts/jobs.py:168,171` `weather_poller` command/cwd | **KI** | job `enabled: False`; jobs.py not touched |
| 8 | `Weather/scripts/ensure-weather-poller.sh:52` G2 weather `.venv` python | **KI/ND** | disabled; Weather import decides venv |
| 9 | `Github/scripts/repos.conf:9-11` `skills` (enabled, inplace), `website`/`mainland` (disabled) | **KI** | G2 repo still synced |
| 10 | `Github/scripts/push-repo-once.sh:33` treats `~/.ollama/skills` as runtime code tree → a pull into G2 skills arms a poller stack reload | **ND** | G2 no longer runs under the poller; decide whether G2 pulls should still reload the stack |
| 11 | `Energy/lib/vendor/README.md:6` `ENERGY_EFLIB_PATH` example → G2 vendor | **ND** | doc; confirm Pacific vendor copy is authoritative |
| 12 | `Communications/telegram/README.md:12` "Live relay today: Legacy ~/.ollama/skills/coms/telegram/" | **MM (doc)** | relay runs from Pacific now |
| 13 | `Geology/README.md:41` | **KI** | guidance text |
| 14 | `*.service.proposed` (globe, BLE), `*.bak-*` | **KI** | historical |

## Database repo
| # | Ref | Class | Note |
| --- | --- | --- | --- |
| 15 | `Logs/Migration/*.md` (6 files) | **KI** | historical evidence, exact paths as observed |
| 16 | `WORKLOG/worklog_current.md`, `WORKLOG/20260929-004259-20260929-010042.md`, `.seen_index`, `.seen_dirs` | **KI** | generated worklog history/index |
| 17 | `Logs/Automations/automations_current.log` (untracked), `Archive/automations_2026-09-29_0100.log` | **KI** | log content |
| — | No code/config in the Database repo references either path. | | |

## Outside the repos
| # | Ref | Class | Note |
| --- | --- | --- | --- |
| 18 | `~/.config/systemd/user/rr-rootserver-poller.service.bak.20260928-162002` ExecStart G2 | **KI** | inactive backup file |
| 19 | Old-root live data still present: `/home/rootrecord/Database/Logs/Automations/{automations_current.log (8.3 MB, frozen 01:11), stack_reload_current.log}`, `/home/rootrecord/Database/intake/council-relay/`, `/home/rootrecord/Database/WORKLOG/`, `/home/rootrecord/Database/ENERGY/`, `/SYSTEM/` | **ND** | archive vs leave; nothing deleted |
| 20 | crontab | — | no matches |

## Correction to an earlier finding
- `Automations/scripts/jobs.py` does **not** reference old-root `Database/intake`: its only intake mention is the header comment on line 6, already the new root (`2 - RootRecord-Database/intake/`). Relay state (`relay.conf` STATE_DIR, `council-relay.py` default) is also new root. No mismatch.

## Counts
Measured after the fixes (grep -rI, 01:36 HST): Pacific old-root 36 line refs in 22 files; `.ollama/skills` 28 line refs in 13 files. Database repo: 12 files (all history/log/worklog content, none code/config). Classified items: FX 4 files · MM 2 (docs) · KI 13 · ND 5 (#1 store.py/ROOTRECORD db, #8 weather venv, #10 G2-pull reload trigger, #11 eflib vendor path, #19 old-root data dirs).

Fixes landed in Pacific `58ee023` (auto-sync 01:35:43 HST).
