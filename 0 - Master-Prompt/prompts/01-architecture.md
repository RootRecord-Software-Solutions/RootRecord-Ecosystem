# Architecture

RootRecord is an ecosystem of multiple repositories and services.

The Master Prompt repository is the cross-project operating contract. It is deliberately not a mirror of application source.

## Source hierarchy

1. Current live/source verification
2. Current repository documentation
3. Durable master-prompt rules
4. Historical handoffs/archives
5. Unverified assumptions

When these disagree, investigate rather than silently choosing one.

## Architecture preservation

Trace existing behavior before replacing it.

For web/data flows, use the actual chain where applicable:

**page → API → handler → data → collector/builder → scheduler/runtime**

For infrastructure work, identify the real service/process/file path before changing anything.

## Historical systems

Old repositories, backups, and archived skills can reveal how something used to work. They do not prove that the same implementation is currently deployed.
