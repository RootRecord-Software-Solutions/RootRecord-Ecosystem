# Handoff

The handoff is a synchronization tool, not the implementation source of truth.

## Required handoff content

### Current state

What is actually true now.

### Changes

Exact files/paths changed and what changed.

### Verification

What was actually tested, including limitations.

### Remaining

Concrete next actions, in order.

### Historical

Important old information that may still matter but must not be mistaken for current state.

## Preferred format

Keep handoffs short enough to operate from.

Do not dump entire source files into a handoff when an exact path, diff summary, or verification result is enough.

## Operator workflow

When a step must be run manually:

- give one paste-safe block;
- use absolute paths;
- preserve backups;
- verify immediately;
- report the evidence.

## Handoff quality standard

Before handoff, independently double-check the work.

The operator should not be the first person to discover that the patch, path, service, or test was wrong.
