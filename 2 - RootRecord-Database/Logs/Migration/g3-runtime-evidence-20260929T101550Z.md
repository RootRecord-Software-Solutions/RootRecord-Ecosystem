# G3 Runtime Evidence Snapshot — 2026-09-29T10:15:50Z (2026-09-29 00:15:50 HST)

Read-only capture from the Pacific desk against Library `Documentation/00-architecture/G3-Runtime-Verification-Runbook-2026-09-28.md`. No restarts, no runtime/config/credential changes, no hardware actions. Secrets, IPs and key paths redacted.

## Scores

| Row | State | Evidence (one line) |
| --- | --- | --- |
| Security/Cameras — cam server | PASS | `:8791` listener `python3 cam_server.py`, cwd Pacific `Security/Cameras`; `/health` HTTP 200 `{"ok": true}`; no cam process under `.ollama/skills` |
| Security/Cameras — frame grab | PASS | `security_camera_frame_grab` wrote ch1–ch4 JPEGs to `2 - RootRecord-Database/Media/Images/` at ~00:12 HST; Pacific tree clean; `store/` (CONNECTION.json) git-ignored |
| Security/Cameras — timelapse | VERIFY PENDING | hourly/catchup ran from Pacific but only skipped (outside 05–19 window / no frames); no compile/render observed |
| Telegram council relay | FAIL | 0 `council-relay.py` processes; relay log = 74× `No data: poll token`; `TELEGRAM_AVA_TOKEN` absent (SECRETS_1 file missing, SECRETS_2 has no Telegram key); `ensure-relay.sh` still logs `[ok] started` |
| Energy actions | VERIFY PENDING | Pacific `Energy/scripts/actions/*` present/executable; no `solar-gate-status` run in log (not executed — read-only capture) |
| Energy BLE owner (read side) | FAIL (legacy runtime) | `ava-ecoflow-ble.service` runs G2 `~/.ollama/skills/energy/scripts/ble/ble-owner.py`; Pacific `Energy/config/devices.conf` `owner_script` points there; no Pacific ble-owner.py |
| System sampling | PASS | `sys_stats_cycle` RUN Pacific `System/scripts/sys-sample.sh` → `OK wrote /home/rootrecord/Database/SYSTEM/samples/…json` every ~5 s |
| Reports worklog | PASS | `worklog_scan` RUN Pacific `Reports/scripts/worklog_once.sh` → `OK wrote/updated /home/rootrecord/Database/WORKLOG/worklog_current.md` |
| Plumbing non-NPU | VERIFY PENDING | `ollama_warmup` from Pacific `System/scripts/plumbing/` → `[ok] ollama up`; :11434 HTTP 200; no inference through Pacific single-flight — state dir `/home/rootrecord/Database/GITHUB/plumbing/state` does not exist |
| Plumbing NPU (FLM) | BLOCKED | no `flm` binary under $HOME, no `~/.local/opt/fastflowlm`, no `ava-flm.service`; :52625 unreachable; job logs `[skip] FLM binary not found` |
| Network globe | FAIL | `network-globe-hawaii.service` ExecStart/WorkingDirectory = G2 `~/.ollama/skills/coms/ssh/local-data-globe/collector.js` (running); Pacific has no collector |
| systemd ExecStart (poller) | PASS | `rr-rootserver-poller.service` (user) ExecStart `/bin/bash "<Pacific>/Automations/scripts/poller/run-poller.sh"`; MainPID = Pacific `rootserver_poller.py`, which imports `jobs` from its own dir |
| Pacific poller §5 | FAIL | poller + log path Pacific, log fresh, 0 `.ollama/skills` refs in last 3000 lines, no FAIL storm — but active Network Globe resolves to legacy runtime and Telegram relay not running |
| Precondition: single poller | PASS | exactly one `rootserver_poller.py` (Pacific); no G2 poller process |
| Precondition: single relay | PASS (no duplicate) | 0 relay processes — no second getUpdates owner (relay itself FAILs above) |
| Precondition: single cloudflared | PASS | 1 `cloudflared` process, Pacific `Communications/network/cloudflare/bin/cloudflared`, child of poller |

## Raw evidence (sanitized)

Scores above reflect the 2026-09-29T10:12–10:16Z capture; raw block re-captured at 2026-09-29T10:16:27Z (2026-09-29 00:16:27 HST).

```text
--- poller processes
pid=651134 ppid=2816 start="Mon Sep 28 23:40:20 2026"
  cmd: /usr/bin/python3 /home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Automations/scripts/rootserver_poller.py 
  cwd: /home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server
--- processes with cwd/cmdline under .ollama/skills (excluding shells)
pid=3001 ppid=2816 start="Mon Sep 28 19:30:00 2026"
  cmd: /usr/bin/python3 /home/rootrecord/.ollama/skills/energy/scripts/ble/ble-owner.py 
  cwd: /home/rootrecord
pid=651628 ppid=2816 start="Mon Sep 28 23:40:21 2026"
  cmd: /usr/bin/node /home/rootrecord/.ollama/skills/coms/ssh/local-data-globe/collector.js 
  cwd: /home/rootrecord/.ollama/skills/coms/ssh/local-data-globe
pid=701599 ppid=651628 start="Tue Sep 29 00:10:30 2026"
  cmd: ssh -T -o BatchMode=yes -o ConnectTimeout=8 -o ServerAliveInterval=15 -o ServerAliveCountMax=3 -o StrictHostKeyChecking=accept-new -i [KEY] -p 22 [USER]@[IP] mkdir -p '/home/ubuntu/network-globe/network-globe/data' && printf '%s\n' '__NETWO
  cwd: /home/rootrecord/.ollama/skills/coms/ssh/local-data-globe
--- port 8791
LISTEN 0      5      [IP]:8791 [IP]:* users:(("python3",pid=651506,fd=3))
pid=651506 ppid=2816 start="Mon Sep 28 23:40:21 2026"
  cmd: python3 cam_server.py 
  cwd: /home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Security/Cameras
health: {"ok": true, "service": "a-eyes-cam-server"}
--- council-relay.py python processes: 0
--- relay log (sanitized, counted)
     74 No data: poll token
--- relay secret sources (existence / key-name counts only)
SECRETS_1 /home/rootrecord/.config/ava-council/secrets.env: MISSING
SECRETS_2 master-key.env: TELEGRAM_AVA_TOKEN=0 AVA_TELEGRAM_BOT_TOKEN=0 TELEGRAM_BOT_TOKEN=0
--- cloudflared count: 1
pid=651141 ppid=651134 start="Mon Sep 28 23:40:20 2026"
  cmd: /home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Communications/network/cloudflare/bin/cloudflared tunnel --no-autoupdate run 
  cwd: /home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server
--- systemd user units
[rr-rootserver-poller.service] active MainPID=651134
# /home/rootrecord/.config/systemd/user/rr-rootserver-poller.service
WorkingDirectory=/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server
ExecStart=/bin/bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Automations/scripts/poller/run-poller.sh"
# /home/rootrecord/.config/systemd/user/rr-rootserver-poller.service.d/logging.conf
[network-globe-hawaii.service] active MainPID=651628
# /home/rootrecord/.config/systemd/user/network-globe-hawaii.service
WorkingDirectory=/home/rootrecord/.ollama/skills/coms/ssh/local-data-globe
ExecStart=/usr/bin/node /home/rootrecord/.ollama/skills/coms/ssh/local-data-globe/collector.js
[ava-ecoflow-ble.service] active MainPID=3001
# /home/rootrecord/.config/systemd/user/ava-ecoflow-ble.service
ExecStart=/usr/bin/python3 /home/rootrecord/.ollama/skills/energy/scripts/ble/ble-owner.py
--- FLM / plumbing
flm binaries under $HOME: 0; ~/.local/opt/fastflowlm: absent; ava-flm.service: absent; :52625 http=000; :11434 http=200
plumbing state dir /home/rootrecord/Database/GITHUB/plumbing/state: absent
--- energy
solar-gate-status run lines in log (excluding sync diffstats): 0
2026-09-29T00:15:32-10:00  ▸  SUMMARY=delta2 soc=100% solar=0W ac_out=0W usbc=0W src=api db=ok
2026-09-29T00:16:17-10:00  ▸  SUMMARY=river2pro soc=26% solar=0W ac_out=73W usbc=56W src=api db=ok
98:owner_script=/home/rootrecord/.ollama/skills/energy/scripts/ble/ble-owner.py
--- per-job latest lines
2026-09-28T23:40:21-10:00job:security_camera_server RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Security/Cameras/ensure_cam_server.sh"
2026-09-28T23:40:22-10:00job:security_camera_server | security cam server started
2026-09-29T00:16:16-10:00job:security_camera_frame_grab | 00:16:12  📷  a-eyes  ch3 → ch3-20260929T101612Z.jpg  (11.6 KB, 1.70s)
2026-09-29T00:16:16-10:00job:security_camera_frame_grab | 00:16:14  📷  a-eyes  ch4 → ch4-20260929T101614Z.jpg  (11.5 KB, 1.81s)
2026-09-28T23:40:22-10:00job:security_timelapse_catchup | 23:40:22  🎬  a-eyes-timelapse  hour 18 has no frames yet, skipping
2026-09-28T23:40:22-10:00job:security_timelapse_catchup | 23:40:22  🎬  a-eyes-timelapse  daily render: no hourly chunks found, nothing to stitch
2026-09-29T00:00:55-10:00job:security_timelapse_hourly_compile RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Security/Cameras/timelapse_hourly.sh"
2026-09-29T00:00:55-10:00job:security_timelapse_hourly_compile | 00:00:55  🎬  a-eyes-timelapse  hour 23 is outside the 05:00-19:00 window, skipping
2026-09-28T23:40:21-10:00job:council_relay RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Communications/telegram/scripts/ensure-relay.sh"
2026-09-28T23:40:21-10:00job:council_relay | [ok] council-relay started pid=651444 → /home/rootrecord/Database/GITHUB/logs/council-relay.log
2026-09-29T00:16:19-10:00job:sys_stats_cycle | SYSTEM  cpu=12%  load=1.97  mem=74%  src=sqlite
2026-09-29T00:16:19-10:00job:sys_stats_cycle | OK wrote /home/rootrecord/Database/SYSTEM/samples/sys-20260929-001618.json
2026-09-29T00:16:00-10:00job:worklog_scan RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Reports/scripts/worklog_once.sh"
2026-09-29T00:16:03-10:00job:worklog_scan | OK wrote/updated /home/rootrecord/Database/WORKLOG/worklog_current.md
2026-09-28T23:40:21-10:00job:ollama_warmup RUN  bash '/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/System/scripts/plumbing/ollama-warmup.sh'
2026-09-28T23:40:21-10:00job:ollama_warmup | [ok] ollama up
2026-09-28T23:40:21-10:00job:flm_npu_warmup RUN  bash '/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/System/scripts/plumbing/flm-warmup.sh'
2026-09-28T23:40:21-10:00job:flm_npu_warmup | [skip] FLM binary not found (NPU path optional; Ollama remains fallback)
2026-09-28T23:40:23-10:00job:network_globe_hawaii | [ensure-network-globe-hawaii] starting network-globe-hawaii.service
2026-09-28T23:40:23-10:00job:network_globe_hawaii | [ensure-network-globe-hawaii] verify: network-globe-hawaii.service is active
--- frames (latest 4 in Database Media/Images)
2026-09-29 00:16:15 ch4-20260929T101614Z.jpg
2026-09-29 00:16:13 ch3-20260929T101612Z.jpg
2026-09-29 00:16:12 ch2-20260929T101608Z.jpg
2026-09-29 00:16:07 ch1-20260929T101603Z.jpg
--- Pacific git status Security/Cameras (incl. ignored)
!! Security/Cameras/__pycache__/
!! Security/Cameras/store/
--- log freshness
mtime=2026-09-29 00:16:19.115640567 -1000 last_line=2026-09-29T00:16:19-10:00 skills_refs_last3000=0 FAIL_last3000=1
2026-09-28T23:39:46-10:00job:github_sync_all FAIL code=-15
--- automations_current.log tail -n 80 (sanitized)
2026-09-29T00:14:50-10:00job:security_camera_frame_grab | 00:14:48  📷  a-eyes  ch4 → ch4-20260929T101448Z.jpg  (11.5 KB, 1.51s)
2026-09-29T00:14:51-10:00  ▸  SUMMARY=delta2 soc=100% solar=0W ac_out=0W usbc=0W src=api db=ok
2026-09-29T00:14:51-10:00job:sys_stats_cycle RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/System/scripts/sys-sample.sh"
2026-09-29T00:14:53-10:00job:sys_stats_cycle | SYSTEM  cpu=15%  load=1.76  mem=74%  src=sqlite
2026-09-29T00:14:53-10:00job:sys_stats_cycle | OK wrote /home/rootrecord/Database/SYSTEM/samples/sys-20260929-001452.json
2026-09-29T00:15:19-10:00job:github_sync_all | — [pacific] no local changes
2026-09-29T00:15:19-10:00job:github_sync_all | ↓ [pacific] fetching origin/main
2026-09-29T00:15:19-10:00job:github_sync_all | From github.com:RootRecord-Software-Solutions/RootRecord-Pacific-Solar-Server
2026-09-29T00:15:19-10:00job:github_sync_all | * branch            main       -> FETCH_HEAD
2026-09-29T00:15:19-10:00job:github_sync_all | — [pacific] local and GitHub already match
2026-09-29T00:15:19-10:00job:github_sync_all | — [pacific] nothing to push
2026-09-29T00:15:19-10:00job:github_sync_all | ↑ [database] committed 4 local file(s)
2026-09-29T00:15:19-10:00job:github_sync_all | ↓ [database] fetching origin/main
2026-09-29T00:15:19-10:00job:github_sync_all | From github.com:RootRecord-Software-Solutions/RootRecord-Database
2026-09-29T00:15:19-10:00job:github_sync_all | * branch            main       -> FETCH_HEAD
2026-09-29T00:15:19-10:00job:github_sync_all | ↑ [database] local is ahead of GitHub
2026-09-29T00:15:19-10:00job:github_sync_all | To github.com:RootRecord-Software-Solutions/RootRecord-Database.git
2026-09-29T00:15:19-10:00job:github_sync_all | 050f908..25b87b8  HEAD -> main
2026-09-29T00:15:19-10:00job:github_sync_all | branch 'main' set up to track 'origin/main'.
2026-09-29T00:15:19-10:00job:github_sync_all | ↑ [database] 4 files → RootRecord-Software-Solutions/RootRecord-Database (main)
2026-09-29T00:15:19-10:00job:github_sync_all | — [library] no local changes
2026-09-29T00:15:19-10:00job:github_sync_all | ↓ [library] fetching origin/main
2026-09-29T00:15:19-10:00job:github_sync_all | From github.com:RootRecord-Software-Solutions/RootRecord-Library
2026-09-29T00:15:19-10:00job:github_sync_all | * branch            main       -> FETCH_HEAD
2026-09-29T00:15:19-10:00job:github_sync_all | — [library] local and GitHub already match
2026-09-29T00:15:19-10:00job:github_sync_all | — [library] nothing to push
2026-09-29T00:15:19-10:00job:github_sync_all | — [skills] no local changes
2026-09-29T00:15:19-10:00job:github_sync_all | ↓ [skills] fetching origin/main
2026-09-29T00:15:19-10:00job:github_sync_all | From github.com:rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server
2026-09-29T00:15:19-10:00job:github_sync_all | * branch            main       -> FETCH_HEAD
2026-09-29T00:15:19-10:00job:github_sync_all | — [skills] local and GitHub already match
2026-09-29T00:15:19-10:00job:github_sync_all | — [skills] nothing to push
2026-09-29T00:15:19-10:00job:security_camera_frame_grab RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Security/Cameras/grab_all.sh"
2026-09-29T00:15:31-10:00job:security_camera_frame_grab | 00:15:19  📷  a-eyes  ch1 → ch1-20260929T101519Z.jpg  (289.9 KB, 4.34s)
2026-09-29T00:15:31-10:00job:security_camera_frame_grab | 00:15:23  📷  a-eyes  ch2 → ch2-20260929T101523Z.jpg  (371.8 KB, 4.55s)
2026-09-29T00:15:31-10:00job:security_camera_frame_grab | 00:15:28  📷  a-eyes  ch3 → ch3-20260929T101528Z.jpg  (11.6 KB, 1.53s)
2026-09-29T00:15:31-10:00job:security_camera_frame_grab | 00:15:29  📷  a-eyes  ch4 → ch4-20260929T101529Z.jpg  (11.6 KB, 1.71s)
2026-09-29T00:15:31-10:00ENERGY  status=live  B2=100%  B1=26%  solar=0 W  ac=0 W  usbc=0 W  src=/home/rootrecord/Database/ENERGY
2026-09-29T00:15:32-10:00  ▸  SUMMARY=delta2 soc=100% solar=0W ac_out=0W usbc=0W src=api db=ok
2026-09-29T00:15:32-10:00job:sys_stats_cycle RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/System/scripts/sys-sample.sh"
2026-09-29T00:15:34-10:00job:sys_stats_cycle | SYSTEM  cpu=18%  load=1.24  mem=74%  src=sqlite
2026-09-29T00:15:34-10:00job:sys_stats_cycle | OK wrote /home/rootrecord/Database/SYSTEM/samples/sys-20260929-001533.json
2026-09-29T00:16:00-10:00job:github_sync_all | — [pacific] no local changes
2026-09-29T00:16:00-10:00job:github_sync_all | ↓ [pacific] fetching origin/main
2026-09-29T00:16:00-10:00job:github_sync_all | From github.com:RootRecord-Software-Solutions/RootRecord-Pacific-Solar-Server
2026-09-29T00:16:00-10:00job:github_sync_all | * branch            main       -> FETCH_HEAD
2026-09-29T00:16:00-10:00job:github_sync_all | — [pacific] local and GitHub already match
2026-09-29T00:16:00-10:00job:github_sync_all | — [pacific] nothing to push
2026-09-29T00:16:00-10:00job:github_sync_all | ↑ [database] committed 4 local file(s)
2026-09-29T00:16:00-10:00job:github_sync_all | ↓ [database] fetching origin/main
2026-09-29T00:16:00-10:00job:github_sync_all | From github.com:RootRecord-Software-Solutions/RootRecord-Database
2026-09-29T00:16:00-10:00job:github_sync_all | * branch            main       -> FETCH_HEAD
2026-09-29T00:16:00-10:00job:github_sync_all | ↑ [database] local is ahead of GitHub
2026-09-29T00:16:00-10:00job:github_sync_all | To github.com:RootRecord-Software-Solutions/RootRecord-Database.git
2026-09-29T00:16:00-10:00job:github_sync_all | 25b87b8..0d38792  HEAD -> main
2026-09-29T00:16:00-10:00job:github_sync_all | branch 'main' set up to track 'origin/main'.
2026-09-29T00:16:00-10:00job:github_sync_all | ↑ [database] 4 files → RootRecord-Software-Solutions/RootRecord-Database (main)
2026-09-29T00:16:00-10:00job:github_sync_all | — [library] no local changes
2026-09-29T00:16:00-10:00job:github_sync_all | ↓ [library] fetching origin/main
2026-09-29T00:16:00-10:00job:github_sync_all | From github.com:RootRecord-Software-Solutions/RootRecord-Library
2026-09-29T00:16:00-10:00job:github_sync_all | * branch            main       -> FETCH_HEAD
2026-09-29T00:16:00-10:00job:github_sync_all | — [library] local and GitHub already match
2026-09-29T00:16:00-10:00job:github_sync_all | — [library] nothing to push
2026-09-29T00:16:00-10:00job:github_sync_all | — [skills] no local changes
2026-09-29T00:16:00-10:00job:github_sync_all | ↓ [skills] fetching origin/main
2026-09-29T00:16:00-10:00job:github_sync_all | From github.com:rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server
2026-09-29T00:16:00-10:00job:github_sync_all | * branch            main       -> FETCH_HEAD
2026-09-29T00:16:00-10:00job:github_sync_all | — [skills] local and GitHub already match
2026-09-29T00:16:00-10:00job:github_sync_all | — [skills] nothing to push
2026-09-29T00:16:00-10:00job:worklog_scan RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Reports/scripts/worklog_once.sh"
2026-09-29T00:16:03-10:00job:worklog_scan | OK wrote/updated /home/rootrecord/Database/WORKLOG/worklog_current.md
2026-09-29T00:16:03-10:00job:security_camera_frame_grab RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Security/Cameras/grab_all.sh"
2026-09-29T00:16:16-10:00job:security_camera_frame_grab | 00:16:03  📷  a-eyes  ch1 → ch1-20260929T101603Z.jpg  (290.2 KB, 4.73s)
2026-09-29T00:16:16-10:00job:security_camera_frame_grab | 00:16:08  📷  a-eyes  ch2 → ch2-20260929T101608Z.jpg  (372.5 KB, 4.38s)
2026-09-29T00:16:16-10:00job:security_camera_frame_grab | 00:16:12  📷  a-eyes  ch3 → ch3-20260929T101612Z.jpg  (11.6 KB, 1.70s)
2026-09-29T00:16:16-10:00job:security_camera_frame_grab | 00:16:14  📷  a-eyes  ch4 → ch4-20260929T101614Z.jpg  (11.5 KB, 1.81s)
2026-09-29T00:16:17-10:00  ▸  SUMMARY=river2pro soc=26% solar=0W ac_out=73W usbc=56W src=api db=ok
2026-09-29T00:16:17-10:00job:sys_stats_cycle RUN  bash "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/System/scripts/sys-sample.sh"
2026-09-29T00:16:19-10:00job:sys_stats_cycle | SYSTEM  cpu=12%  load=1.97  mem=74%  src=sqlite
2026-09-29T00:16:19-10:00job:sys_stats_cycle | OK wrote /home/rootrecord/Database/SYSTEM/samples/sys-20260929-001618.json
```
