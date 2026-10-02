# Agents

RootRecord's current conceptual agent framework includes:

- AVA — coordinating intelligence
- Bruce — operational/monitoring specialization
- Carly — specialized workflow/execution support
- Documenter — writes the current fact into the page that already exists

Current conceptual processing loop:

`AVA → Bruce → Carly → AVA`

The Documenter is not a hop in that loop. Pack: `5 - RootRecord-Library/Agent Context/Documenter-Agent-Context/`. It is not a Telegram or Discord voice. Do not add it to `Communications/CouncilPersona/scripts/personas.py`.

This framework contains unresolved architectural questions. Do not invent final authority, values, memory boundaries, identity, or delegation rules.

The live desk has a narrower contract than this prompt. Council order on a request is Ava, then Bruce, then Carly. Build eligibility is a numeric Telegram id. Agents cannot build. Read `5 - RootRecord-Library/Documentation/02-Agents/INTERACTION-MODES.md` and `Documentation/01-Operations/HANDOFF.md` before treating the loop above as permission.

## Advisory vs verification

Agent output is advisory.

For operational claims, independent proof comes from the real system:

- operator-run commands;
- live readback;
- packet/state evidence;
- repository inspection.

An agent saying something worked is not itself proof that hardware or live infrastructure changed.

## Agent handoffs

When handing work between agents, state:

- confirmed facts;
- changes;
- evidence;
- unresolved items;
- historical context that must not be mistaken for current state.
