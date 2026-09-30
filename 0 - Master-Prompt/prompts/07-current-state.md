# Current State

## Baseline

The RootRecord ecosystem currently spans:

- Solar Pacific RootRecord Server
- US Mainland Server
- RootRecord Website
- RootRecord Master Prompt

The canonical RootRecord master-prompt area in this repository is now:

`0-master-prompt/`

## Live-state layer

The machine-readable current snapshot is:

`0-master-prompt/state/state.json`

The historical recorder is:

`0-master-prompt/logs/state-history.json`

The intended refresh interval is five minutes.

The snapshot and history are context/provenance records. They do not replace direct verification when an operation depends on live hardware, services, endpoints, or deployed runtime state.

## Automated deploy path (skills)

**Confirmed:** Desk `github_sync_all` (~every 300s) bidirectionally syncs skills. When GitHub has new skills commits, they are merged locally and the **poller stack auto-reloads** (full stop of poller + cloudflared + watch, then start). BLE owner unit is left alone.

AIs must not suggest parallel restarts or dual processes for ordinary code deploys.

## Energy data layer (2026-09-24)

- Canonical SQLite: `/home/rootrecord/Database/ROOTRECORD/rootrecord.db`
- Dual-write producer: `energy/lib/read_runner.py` (SQLite then legacy JSON)
- Poller `/energy` prefers SQLite; EcoFlow reads scheduled ONCE_AT_START + every 15 minutes
- B3 = expansion battery under Delta 2 (documented in `devices.conf`); no invented MAC

## Cross-project operating principles

- Constant self and community improvement is the documented RootRecord mission.
- Data stewardship is a central architectural principle.
- Local-first processing is preferred where practical.
- Resilience and independent verification matter.
- Historical records should remain distinct from current architecture.
- AVA v0.04 remains an active architectural-definition phase.

## Repository reset boundary

The 2026-09-22/23 handoff documents a significant Solar Pacific skills-folder reset and distinguishes current rebuilt content from older archived implementations.

Therefore:

- current `main` source is current source;
- old archives are historical reference;
- a more mature implementation found only in an old archive must not be called live until verified.

## Operational verification boundary

The 2026-09-23 energy work established real PASS results for Delta 2 DC, USB, and AC with documented caveats. Other listed toggles remained untested at that checkpoint.

This is a historical checkpoint and must be revalidated before being reported as today's live state.
