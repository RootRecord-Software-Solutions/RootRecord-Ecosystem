# Repository & Frequently Touched File Links

This file is the navigation index for major RootRecord files that are repeatedly inspected or modified.

**Rule:** these are links to the real source files. Do not copy those files into the Master Prompt directory.

## Current desk (2026-09-30)

Operator remaining work: `5 - RootRecord-Library/Documentation/01-operations/2026-09-30-whats-left-for-alexander.md`.

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
| Mainland | Continuity directory inside the umbrella | Desk path `1 - Servers/2 - RootRecord-US-Mainland-Server/`. GitHub `rootrecordsoftwaresolutions/US-Mainland-Server` exists. This directory is not its own git repository | Recovery and mainland files in the umbrella | A nested `.git`. The `mainland` sync row is disabled and its configured path is an old snapshot, not this directory |
| Website | Public page, Vercel source | Desk path `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Website/Home/`. Published by the `website` mirror row to https://github.com/RootRecord-Software-Solutions/RootRecord-Website | `index.html`, `vercel.json`, this folder's README | A nested `.git`. Desk scripts stay in `Website/` outside `Home/` and publish with Pacific. `3 - RootRecord-Website/` is not on this desk. Do not bind port 3001. Do not call port 8787. `www` is the AWS globe only until DNS moves. Data contract: `Website/HANDOFF-vercel-homepage-2026-09-30.md`. `rootserver.rootrecord.cloud` is the poller on `:8799` |
| Weather data | Hawaiʻi weather publication | GitHub `rootrecordsoftwaresolutions/RootRecord-Weather-Database` | Published weather products | The Pacific weather daemon's local tree, which stays under Database `Weather/` and is not auto-published from the umbrella |

`/home/rootrecord/Database/` is not on this desk. Sync flags are `2 - RootRecord-Database/Github/flags/`.

Sync catalog: `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Github/scripts/repos.conf`. Enabled rows are `ecosystem`, `pacific`, `database`, `library`, and `website`. `skills` and `mainland` are disabled. Pacific, Database, and Library are mirror publishes of the live folders. `website` mirror-publishes `Website/Home/` to `RootRecord-Software-Solutions/RootRecord-Website`. Deeper narrative: `5 - RootRecord-Library/Documentation/03-Pacific-Server-Current-Architecture/Repository-Ownership-Model.md`.

Publication, short form (WO-DATA, 2026-09-29): energy samples, system samples, worklogs, logs, weather daemon output, and camera media stay local. The skip list is `Github/scripts/ecosystem-skip-autocommit.txt`. Geology SQLite stays local. Geology `*-last.json` and `Daily/*.jsonl` are still tracked; that publication is not signed off. Users/PII retention and timelapse-master retention are still open. Full labels: `5 - RootRecord-Library/Documentation/06-development/Work-Orders/Database_Boundary_Work_Order_WO-DATA-2026-09-27.md`.

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

## US Mainland Server

Repository:
https://github.com/rootrecordsoftwaresolutions/US-Mainland-Server

### Network globe / recovery

- [`mirror/network-globe/server.js`](https://github.com/rootrecordsoftwaresolutions/US-Mainland-Server/blob/main/mirror/network-globe/server.js)
- [`mirror/network-globe/HAWAII-MERGE.md`](https://github.com/rootrecordsoftwaresolutions/US-Mainland-Server/blob/main/mirror/network-globe/HAWAII-MERGE.md)
- [`mirror/network-globe/start.sh`](https://github.com/rootrecordsoftwaresolutions/US-Mainland-Server/blob/main/mirror/network-globe/start.sh)
- [`mirror/network-globe/package.json`](https://github.com/rootrecordsoftwaresolutions/US-Mainland-Server/blob/main/mirror/network-globe/package.json)
- [`mirror/network-globe/README.md`](https://github.com/rootrecordsoftwaresolutions/US-Mainland-Server/blob/main/mirror/network-globe/README.md)
- [`RECOVERY.md`](https://github.com/rootrecordsoftwaresolutions/US-Mainland-Server/blob/main/RECOVERY.md)

Important: the repository is a mirror/recovery representation. Verify the deployed AWS path before treating a GitHub mirror file as live runtime state.

## RootRecord Website

Repository:
https://github.com/RootRecord-Software-Solutions/RootRecord-Website

The old `rootrecordsoftwaresolutions/RootRecord-Website` tree is not this site. Edit the desk folder. The sync publishes it.

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
