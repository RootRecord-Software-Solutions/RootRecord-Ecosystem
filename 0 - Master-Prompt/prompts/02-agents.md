# Agents

RootRecord's current conceptual agent framework includes:

- AVA — coordinating intelligence
- Bruce — operational/monitoring specialization
- Carly — specialized workflow/execution support

Current conceptual processing loop:

`AVA → Bruce → Carly → AVA`

This framework contains unresolved architectural questions. Do not invent final authority, values, memory boundaries, identity, or delegation rules.

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
