# RootRecord Master Prompt

This directory is the durable cross-project operating context and live-state layer for RootRecord development and operations.

It is **not** a copy of the application repositories. It contains:

- rules and workflow conventions;
- repository and frequently-touched-file links;
- durable cross-project context;
- machine-readable current state;
- machine-readable state history;
- prompt manifests and the validation/bundle workflow.

## Directory map

```
0-master-prompt/
├── MASTER-PROMPT.md
├── README.md
├── prompts/
├── state/
│   └── state.json
├── logs/
│   └── state-history.json
├── manifest/
│   └── prompts.yaml
├── skill/
│   └── SKILL.md
└── .github/
    └── workflows/
        └── build-bundle.yml
```

## Repositories

- Solar Pacific RootRecord Server: https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server
- US Mainland Server: https://github.com/rootrecordsoftwaresolutions/US-Mainland-Server
- RootRecord Website: https://github.com/rootrecordsoftwaresolutions/RootRecord-Website
- RootRecord Master Prompt: https://github.com/rootrecordsoftwaresolutions/RootRecord-Master-Prompt

## How to use this directory

Start with:

1. `MASTER-PROMPT.md`
2. `state/state.json` for current machine-readable context
3. `logs/state-history.json` when historical state matters
4. `prompts/00-core.md`
5. `prompts/03-development.md` or `prompts/04-operations.md`
6. `prompts/05-energy.md` when energy/EcoFlow work is involved
7. `prompts/06-handoff.md`
8. `prompts/08-repository-and-file-links.md` when navigating code

`manifest/prompts.yaml` is the prompt inventory.

## Live-state contract

`state/state.json` is the current snapshot.

`logs/state-history.json` is the historical recorder.

The intended refresh cadence is **every five minutes** when the updater is running.

A five-minute record is allowed even when no field changed. That history is intentional and machine-readable.

Every measured or operational value should carry enough provenance to distinguish:

- current vs stale;
- observed vs inferred;
- reachable vs disconnected;
- confirmed vs unknown.

Missing telemetry must remain explicit. Never fill an unavailable value with a guess.

## Important distinction

This directory records **how RootRecord work should be done**, **what is currently known**, and **where important implementation files live**.

The actual implementation remains in the project repositories. When a linked file moves, update this index rather than copying the implementation into this directory.

## Current workflow philosophy

The preferred loop is:

**inspect → patch → validate → independently double-check → hand off**

The agent should work efficiently, reuse the existing architecture, and avoid making the operator discover preventable mistakes.

## Evidence labels

Use these consistently:

- **Confirmed** — directly verified in the current source/live result.
- **Hypothesis** — plausible interpretation that still needs verification.
- **Unknown** — not established.
- **Historical** — preserved for context but not treated as current state.

## No silent reconciliation

When old handoff material conflicts with current implementation, do not silently rewrite history. Identify the conflict, inspect the current source, and record the resulting current state separately.
