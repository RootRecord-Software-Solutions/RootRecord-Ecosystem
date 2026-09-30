---
name: rootrecord-master-prompt
description: Load the RootRecord Master Prompt, current machine-readable state, and applicable prompt files before RootRecord development or operational work.
---

# RootRecord Master Prompt Skill

## Canonical location

https://github.com/rootrecordsoftwaresolutions/Solar-Pacific-RootRecord-Server/tree/main/0-master-prompt

## Procedure

1. Load `MASTER-PROMPT.md`.
2. Load `state/state.json` before making operational claims.
3. Load the applicable prompt files.
4. Load `prompts/08-repository-and-file-links.md` when navigating or changing frequently touched files.
5. Inspect the actual target repository.
6. Classify material facts as Confirmed, Hypothesis, Unknown, or Historical.
7. Patch the existing final path.
8. Validate.
9. Independently double-check.
10. Hand off with exact paths and evidence.

## Live-state rule

The current snapshot is context, not proof that the live system still matches it.

For consequential operational work, independently verify the relevant service, device, endpoint, or hardware state.

The intended state refresh cadence is every five minutes when the updater is running.

## Historical state

`logs/state-history.json` is an operational black-box recorder. Five-minute records are allowed even when nothing changed.

Use `verified_at`, `source`, and explicit `unknown`/`stale`/`offline` states to distinguish observations from assumptions.

## Existing-work rule

Do not create alternate roots, v2 trees, duplicate implementations, or migration scaffolding unless explicitly requested.

The canonical master-prompt directory is `0-master-prompt/`.

## Temporary work

Use `/tmp/` for disposable helpers.

Permanent EcoFlow action scripts remain in the existing energy skill structure.

## Operator workflow

When a manual command is required, follow the established one-paste-safe-block workflow and use absolute paths.
