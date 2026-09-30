# G3 Cutover Evidence — network-globe-hawaii + ava-ecoflow-ble — 2026-09-29T10:37:20Z (2026-09-29 00:37:20 HST)

Operator-approved cutover of two systemd user units from G2 `.ollama/skills` to Pacific. Poller not restarted; no jobs.py or credential change. Unit/config backup: `/home/rootrecord/Database/GITHUB/g3-cutover.bak-20260929-003344/`. IPs and tokens redacted.

| Service | Result | Evidence |
| --- | --- | --- |
| network-globe-hawaii | PASS | Unit repointed 00:33:44 HST (10:33:44Z); new MainPID runs `node <Pacific>/Communications/network/local-data-globe/collector.js`, cwd Pacific; active, NRestarts=0; `SSH stream ready → AWS`. The only warnings (one SSH reconnect at start, remote `maintain-hawaii-feed.sh` missing → exit 127) are identical to the G2 collector's journal before cutover (4× / 89× since 23:40 HST), i.e. pre-existing and remote-side. |
| ava-ecoflow-ble | PASS | Old owner stopped and confirmed gone; unit repointed + `devices.conf` `ble_log`/`owner_script` updated; started 00:35:14 HST (10:35:14Z); MainPID runs `python3 <Pacific>/Energy/scripts/ble/ble-owner.py`; pid file = MainPID; new log has start + heartbeats at 30 s; journal errors 0; exactly one owner process. |

Note: a first BLE attempt at ~00:34:38 HST aborted in its own safety guard (the "old owner still running" check matched the operator shell's command line, not a real process). It restarted the unchanged G2 unit (00:34:40), so the old owner was down about 2 s. The retry used a python-only process match and succeeded.

Note: Energy `B1=0%` in the poller's ENERGY lines started at 00:26:10 HST, before either cutover; it is not caused by this change.

```text
--- network-globe-hawaii
active
ActiveEnterTimestamp=Tue 2026-09-29 00:33:44 HST
MainPID=739721
NRestarts=0
cmd: /usr/bin/node /home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Communications/network/local-data-globe/collector.js 
cwd: /home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Communications/network/local-data-globe
WorkingDirectory=/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Communications/network/local-data-globe
ExecStart=/usr/bin/node "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Communications/network/local-data-globe/collector.js"
2026-09-29T00:33:44-10:00 rootrecord-software-solutions systemd[2816]: Stopping network-globe-hawaii.service - RootRecord Hawaii Network Globe Collector...
2026-09-29T00:33:44-10:00 rootrecord-software-solutions systemd[2816]: Stopped network-globe-hawaii.service - RootRecord Hawaii Network Globe Collector.
2026-09-29T00:33:44-10:00 rootrecord-software-solutions systemd[2816]: network-globe-hawaii.service: Consumed 2min 6.690s CPU time over 53min 22.177s wall clock time, 50.5M memory peak.
2026-09-29T00:33:44-10:00 rootrecord-software-solutions systemd[2816]: Started network-globe-hawaii.service - RootRecord Hawaii Network Globe Collector.
2026-09-29T00:33:45-10:00 rootrecord-software-solutions node[739721]: Hawaii data collector → [USER]@[IP]:22/home/ubuntu/network-globe/network-globe/data/hawaii.ndjson
2026-09-29T00:33:45-10:00 rootrecord-software-solutions node[739721]: origin: Hawaii
2026-09-29T00:33:50-10:00 rootrecord-software-solutions node[739721]: SSH stream ready → AWS
2026-09-29T00:33:50-10:00 rootrecord-software-solutions node[739721]: AWS feed is 1290593953 bytes; starting bounded-feed maintenance
2026-09-29T00:33:50-10:00 rootrecord-software-solutions node[739721]: SSH stream closed (code=255, signal=none)
2026-09-29T00:33:53-10:00 rootrecord-software-solutions node[739721]: AWS feed maintenance: bash: /home/ubuntu/network-globe/network-globe/scripts/maintain-hawaii-feed.sh: No such file or directory
2026-09-29T00:33:53-10:00 rootrecord-software-solutions node[739721]: AWS feed maintenance exited code=127
2026-09-29T00:33:57-10:00 rootrecord-software-solutions node[739721]: SSH stream ready → AWS
--- ava-ecoflow-ble
active
ActiveEnterTimestamp=Tue 2026-09-29 00:35:14 HST
MainPID=741782
NRestarts=0
cmd: /usr/bin/python3 /home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Energy/scripts/ble/ble-owner.py 
ExecStart=/usr/bin/python3 "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Energy/scripts/ble/ble-owner.py"
pidfile /home/rootrecord/Database/ENERGY/state/ava-ecoflow-ble.pid = 741782
log /home/rootrecord/Database/Logs/Energy/ava-ecoflow-ble.log:
2026-09-29T00:35:44-10:00 owner: heartbeat ok — atomic actions own short BLE sessions; poll buckets disabled
2026-09-29T00:36:14-10:00 owner: heartbeat ok — atomic actions own short BLE sessions; poll buckets disabled
2026-09-29T00:36:44-10:00 owner: heartbeat ok — atomic actions own short BLE sessions; poll buckets disabled
2026-09-29T00:37:14-10:00 owner: heartbeat ok — atomic actions own short BLE sessions; poll buckets disabled
owner processes: 1
24:ble_log=/home/rootrecord/Database/Logs/Energy/ava-ecoflow-ble.log
98:owner_script=/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Energy/scripts/ble/ble-owner.py
--- processes still running from .ollama/skills: 0
```
