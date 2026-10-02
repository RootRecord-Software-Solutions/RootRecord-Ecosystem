# Repository & Frequently Touched File Links

This file is the navigation index for major RootRecord files that are repeatedly inspected or modified.

**Rule:** these are links to the real source files. Do not copy those files into the Master Prompt directory.

## Current desk (2026-09-30)

Operator remaining work: `5 - RootRecord-Library/Documentation/01-Operations/2026-09-30-whats-left-for-alexander.md`.

Git root: `/home/rootrecord/RootRecord-Ecosystem`  
Remote: https://github.com/RootRecord-Software-Solutions/RootRecord-Ecosystem

- Pacific runtime: `1 - Servers/1 - RootRecord-Pacific-Solar-Server/`
- Jobs catalog: `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Automations/scripts/jobs.py`
- GitHub sync catalog: `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Github/scripts/repos.conf`
- Runtime skip list: `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Github/scripts/ecosystem-skip-autocommit.txt`
- Database tree: `2 - RootRecord-Database/`
- Library: `5 - RootRecord-Library/`
- This contract: `0 - Master-Prompt/MASTER-PROMPT.md`

## Ownership contract

Answer "where does this go?" from this section. Library holds the history and the why.

Boundary rule: knowledge goes to Library, executable runtime goes to Pacific, bytes go to Database, and the public snapshot goes to the Ecosystem git root. Do not put secrets or live telemetry in the public umbrella. Do not put generated telemetry dumps in Library. Do not add a nested `.git` inside the umbrella.

| Home | Purpose | Where | Contains | Does not contain |
| --- | --- | --- | --- | --- |
| Ecosystem | Public context and this desk's git root | `/home/rootrecord/RootRecord-Ecosystem` · https://github.com/RootRecord-Software-Solutions/RootRecord-Ecosystem | Sanitized snapshot, cross-system docs | Nested clones, live telemetry auto-commits, secrets |
| Library | Knowledge | `5 - RootRecord-Library/` · https://github.com/RootRecord-Software-Solutions/RootRecord-Library | Architecture, work orders, agent context, guides | Runtime code, databases, camera media |
| Pacific | Runtime | `1 - Servers/1 - RootRecord-Pacific-Solar-Server/` · https://github.com/RootRecord-Software-Solutions/RootRecord-Pacific-Solar-Server | Services, jobs, automation, monitors | The data files those jobs write |
| Database | Persistence | `2 - RootRecord-Database/` · https://github.com/RootRecord-Software-Solutions/RootRecord-Database | Logs, media layout, telemetry, canonical data paths | Application logic |
| Mainland One | Continuity directory inside the umbrella | Desk path `1 - Servers/2 - RootRecord-US-Mainland-One/`. GitHub `RootRecord-Software-Solutions/US-Mainland-One`. The `mainland` row publishes this folder | Recovery and mainland files in the umbrella | A nested `.git` |
| Mainland Two | YouTube station, its own repository | Desk path `1 - Servers/3 - RootRecord-US-Mainland-Two/`. GitHub `RootRecord-Software-Solutions/US-Mainland-Two` | Station code and configuration | Generated media. The umbrella gitignores this checkout |
| Website | Public page, Vercel source | Desk path `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Website/Home/`. `website` publishes it to https://github.com/RootRecord-Software-Solutions/RootRecord-Website. `website-personal` publishes the same folder to https://github.com/rootrecordsoftwaresolutions/RootRecord-Website | `index.html`, `vercel.json`, this folder's README | A nested `.git`. Desk scripts stay in `Website/` outside `Home/` and publish with Pacific. `3 - RootRecord-Website/` is not on this desk. Do not bind port 3001. Do not call port 8787. `www` is `https://www.rootrecord.cloud/` on Vercel. Do not point `www` at the Mainland tunnel. `ssh.rootrecord.cloud` is retired. Mainland One is radio. The listener stream is `https://radio.rootrecord.cloud/radio/live.mp3`. `api.rootrecord.cloud` is aimed at Mainland Two and is not live yet. Reports are at `https://www.rootrecord.cloud/reports/`. Data contract: `Website/HANDOFF-vercel-homepage-2026-09-30.md`. `rootserver.rootrecord.cloud` is the Pacific poller on `:8799` |
| Weather data | Hawaiʻi weather publication | GitHub `rootrecordsoftwaresolutions/RootRecord-Weather-Database` | Published weather products | The Pacific weather daemon's local tree, which stays under Database `Weather/` and is not auto-published from the umbrella |

`/home/rootrecord/Database/` is not on this desk. Sync flags are `2 - RootRecord-Database/Github/flags/`.

Sync catalog: `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Github/scripts/repos.conf`. Enabled rows are `ecosystem`, `pacific`, `database`, `library`, `website`, `website-personal`, and `mainland`. `skills` is disabled. Pacific, Database, and Library are mirror publishes of the live folders. `website` and `website-personal` both mirror-publish `Website/Home/`, to the org and to the personal account. Deeper narrative: `5 - RootRecord-Library/Documentation/12-Pacific-Server-Current-Architecture/Repository-Ownership-Model.md`.

Publication, short form (WO-DATA, 2026-09-29): energy samples, system samples, worklogs, logs, weather daemon output, and camera media stay local. The skip list is `Github/scripts/ecosystem-skip-autocommit.txt`. Geology SQLite stays local. Geology `*-last.json` and `Daily/*.jsonl` are still tracked; that publication is not signed off. Users/PII retention and timelapse-master retention are still open. Full labels: `5 - RootRecord-Library/Documentation/06-Development/Work-Orders/Database_Boundary_Work_Order_WO-DATA-2026-09-27.md`.

## Historical links

The sections below point at earlier GitHub homes. Confirm the file still exists there before editing. The live desk copies are the paths above.

## Solar Pacific RootRecord Server

Repository:
https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server

### Master prompt / live-state layer

- [`0-master-prompt/`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/tree/main/0-master-prompt)
- [`0-master-prompt/MASTER-PROMPT.md`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/blob/main/0-master-prompt/MASTER-PROMPT.md)
- [`0-master-prompt/state/state.json`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/blob/main/0-master-prompt/state/state.json)
- [`0-master-prompt/logs/state-history.json`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/blob/main/0-master-prompt/logs/state-history.json)
- [`0-master-prompt/prompts/08-repository-and-file-links.md`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/blob/main/0-master-prompt/prompts/08-repository-and-file-links.md)

The state snapshot is current machine-readable context. The state history is the five-minute operational recorder. Neither replaces direct verification of the live system.

### Development / operating context

- [`Workflow-Rules.md`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/blob/main/Workflow-Rules.md)
- [`00_READ_FIRST.md`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/blob/main/00_READ_FIRST.md)
- [`automations/`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/tree/main/automations)
- [`agents/`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/tree/main/agents)
- [`status/`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/tree/main/status)
- [`coms/`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/tree/main/coms)

### Energy

- [`energy/`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/tree/main/energy)
- [`energy/lib/ble_client.py`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/blob/main/energy/lib/ble_client.py)
- [`automations/scripts/jobs.py`](https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/blob/main/automations/scripts/jobs.py)

## US Mainland One

Repository:
https://github.com/RootRecord-Software-Solutions/US-Mainland-One

Mainland One is radio only. The host runtime is `/home/ubuntu/rootrecord-radio`. Desk SSH is `ml1.rootrecord.cloud` on tunnel Mainland-One (`939b16f7-7d13-4776-bd4d-80fe8021fc72`). `radio.rootrecord.cloud` is HTTP to `127.0.0.1:8092`. The listener stream is `https://radio.rootrecord.cloud/radio/live.mp3`. The station library is Opus. There is no music bed on the live host. The links below are the older globe tree in the repository. They are not the live radio. Earthquake and hurricane voice reports stay on the Pacific poller. Pollers have not moved to Mainland Two.

### Network globe / recovery

- [`mirror/network-globe/server.js`](https://github.com/RootRecord-Software-Solutions/US-Mainland-One/blob/main/mirror/network-globe/server.js)
- [`mirror/network-globe/HAWAII-MERGE.md`](https://github.com/RootRecord-Software-Solutions/US-Mainland-One/blob/main/mirror/network-globe/HAWAII-MERGE.md)
- [`mirror/network-globe/start.sh`](https://github.com/RootRecord-Software-Solutions/US-Mainland-One/blob/main/mirror/network-globe/start.sh)
- [`mirror/network-globe/package.json`](https://github.com/RootRecord-Software-Solutions/US-Mainland-One/blob/main/mirror/network-globe/package.json)
- [`mirror/network-globe/README.md`](https://github.com/RootRecord-Software-Solutions/US-Mainland-One/blob/main/mirror/network-globe/README.md)
- [`RECOVERY.md`](https://github.com/RootRecord-Software-Solutions/US-Mainland-One/blob/main/RECOVERY.md)

Important: the repository is a mirror/recovery representation. Verify the deployed AWS path before treating a GitHub mirror file as live runtime state.

## US Mainland Two

Repository:
https://github.com/RootRecord-Software-Solutions/US-Mainland-Two

Desk checkout: `1 - Servers/3 - RootRecord-US-Mainland-Two/`. This folder has its own git repository. The umbrella ignores it. Tunnel id `bd8e68a4-8a97-4b20-afd9-b058473a0a22`. `ml2.rootrecord.cloud` is SSH. Direct fallback `ml2-ip` is `3.149.238.83`. `api.rootrecord.cloud` is aimed here. The API process is not there yet. Pollers are planned to move here later. They have not moved.

## RootRecord Website

Repository:
https://github.com/RootRecord-Software-Solutions/RootRecord-Website

Personal copy: https://github.com/rootrecordsoftwaresolutions/RootRecord-Website. Edit the desk folder. `github_sync_all` publishes it to both remotes.

- [`README.md`](https://github.com/RootRecord-Software-Solutions/RootRecord-Website/blob/main/README.md)
- [`index.html`](https://github.com/RootRecord-Software-Solutions/RootRecord-Website/blob/main/index.html)
- [`vercel.json`](https://github.com/RootRecord-Software-Solutions/RootRecord-Website/blob/main/vercel.json)

Desk path: `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Website/Home/`

## RootRecord Master Prompt

- [`MASTER-PROMPT.md`](https://github.com/rootrecordsoftwaresolutions/RootRecord-Master-Prompt/blob/main/MASTER-PROMPT.md)
- [`README.md`](https://github.com/rootrecordsoftwaresolutions/RootRecord-Master-Prompt/blob/main/README.md)
- [`prompts/`](https://github.com/rootrecordsoftwaresolutions/RootRecord-Master-Prompt/tree/main/prompts)
- [`manifest/prompts.yaml`](https://github.com/rootrecordsoftwaresolutions/RootRecord-Master-Prompt/blob/main/manifest/prompts.yaml)
- [`skill/SKILL.md`](https://github.com/rootrecordsoftwaresolutions/RootRecord-Master-Prompt/blob/main/skill/SKILL.md)

## Link maintenance rule

When a frequently touched file changes location:

1. update this index;
2. do not create a duplicate copy just to preserve an old link;
3. if the old path is historically important, label it **Historical** and point to the new canonical file;
4. do not claim a link is live/deployed merely because it exists in GitHub.

## State-link maintenance rule

The state files describe observations, not source code.

When a state producer, collector, or updater changes:

1. update the relevant implementation links here;
2. update the state schema/documentation;
3. verify the producer's actual runtime path;
4. do not treat a stale state snapshot as live evidence.
