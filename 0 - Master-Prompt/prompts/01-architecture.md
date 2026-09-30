# Architecture

RootRecord is an ecosystem of multiple roles and services.

On the current desk, `/home/rootrecord/RootRecord-Ecosystem` is one git repository and the public context layer. Library, Pacific runtime, and Database are directories in that tree with separate responsibilities. They are not nested clones.

The Master Prompt directory is the cross-project operating contract. It is not a second copy of application source.

GitHub may still host separate source repositories. This checkout does not. Automated publish goes through the umbrella `ecosystem` sync row.

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
