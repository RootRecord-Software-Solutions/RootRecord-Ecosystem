# Current State

**Checked:** 2026-09-30 01:24 HST. Operator remaining work: `5 - RootRecord-Library/Documentation/01-operations/2026-09-30-whats-left-for-alexander.md`.

## Baseline

The live desk is one git root:

`/home/rootrecord/RootRecord-Ecosystem`

Remote: https://github.com/RootRecord-Software-Solutions/RootRecord-Ecosystem

| Area | Path | Role |
| --- | --- | --- |
| Master Prompt | `0 - Master-Prompt/` | Rules and this state file |
| Pacific runtime | `1 - Servers/1 - RootRecord-Pacific-Solar-Server/` | Poller, jobs, energy, weather, cameras |
| Database | `2 - RootRecord-Database/` | Telemetry, logs, media |
| Website | `3 - RootRecord-Website/` | Local Next.js site (`:3001` at the check) |
| Mainland | `1 - Servers/2 - RootRecord-US-Mainland-Server/` | Desk copy. Sync row disabled |
| Library | `5 - RootRecord-Library/` | Docs, work orders, agent context |
| Node | `4 - RootRecord-Node/` | Placeholder |
| Android | `6 - Android Development/` | App trees. Build still unverified |

The older Solar-Pacific `0-master-prompt/` path is a historical home. This directory is `0 - Master-Prompt/`.

## Live desk (Confirmed, 2026-09-30 01:24 HST)

Host `rootrecord-software-solutions` came up at 01:09 HST. These were up from the Ecosystem Pacific path:

- `rr-rootserver-poller.service`
- EcoFlow BLE owner
- Cloudflare tunnel, public `https://rootserver.rootrecord.cloud` HTTP 200
- Camera server and frame grabs (ch4 is a small night frame)
- Hawaii weather poller (county reports at 01:18; some NOAA pages invalid or 500/503/403)
- Hawaii network globe
- Ollama on `:11434`
- GitHub sync for ecosystem, pacific, database, and library. `skills` matched. `website` and `mainland` rows stay disabled

**Energy.** River 2 Pro BLE is the live pack (about 35% SOC at 01:22, discharging, solar 0 W). Delta 2 died and no longer transmits. `WAITING` and the last snapshot at 1% SOC (00:53 HST, cloud API) are normal. Do not treat that as a migration fault. Do not schedule Delta 2 actuation.

**Telegram.** Relay process is up. Replies stay off until Alexander sets `RR_RELAY_REPLIES=1`.

**Legacy trees.** `~/.ollama/skills` (277 MB) is still the `skills` sync row. The 27 GB `old ollama/old skills` tree is only partly copied into `Old repos deleted and merged/`. Do not delete either without Alexander's explicit sign-off.

Machine-readable copy: `state/state.json` (written 2026-09-30 01:29 HST). It is context. Re-verify hardware and services before an action that depends on them.

## Automated deploy path

**Confirmed:** `github_sync_all` syncs the enabled `repos.conf` rows. When GitHub has new commits for the live runtime, they are merged and the poller stack reloads (full stop of poller + cloudflared + watch, then start). The BLE owner unit is left alone.

Do not suggest a second poller or a manual restart after an ordinary push.

## Cross-project operating principles

- Constant self and community improvement is the documented RootRecord mission.
- Data stewardship is a central architectural principle.
- Local-first processing is preferred where practical.
- Resilience and independent verification matter.
- Historical records should remain distinct from current architecture.

## Historical checkpoints

These are not today's live claim:

- 2026-09-22/23 skills-folder reset. Current `main` is current source. An implementation that exists only in an archive is not live until verified.
- 2026-09-23 energy PASS for Delta 2 DC, USB, and AC. That pack is now dead. Re-verify on River 2 Pro before calling an action PASS.
- 2026-09-24 state file, when it still named `rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server` and left power unknown. Superseded by the 2026-09-30 snapshot above.
