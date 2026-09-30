# RootRecord Ecosystem

> Canonical public umbrella repository for the RootRecord ecosystem.

This repository provides a flattened, sanitized snapshot of the broader RootRecord ecosystem so the architecture, services, infrastructure, documentation, and supporting components can be understood together in one place.

## What this repository is

**RootRecord-Ecosystem is the central context and reference repository.**

It brings together sanitized copies of the ecosystem's major components for cross-system visibility and documentation. It is intended to make the overall system easier to inspect, understand, and maintain without replacing the individual repositories that own each component's source history.

This repository is a **snapshot/context layer**, not the authoritative Git history for every component.

## Source repositories

The underlying components retain their own repositories and histories:

- **RootRecord-Library** — shared library, documentation, and reference material.
- **RootRecord-Database** — database, telemetry, system-state, and supporting data structures.
- **RootRecord-Pacific-Solar-Server** — Pacific Solar server and associated communications/infrastructure components.
- **RootRecord-US-Mainland-Server** — US Mainland server and associated infrastructure components.
- **RootRecord-Cloud** — cloud/web-facing components associated with the Pacific Solar server.

The ecosystem snapshot may contain flattened copies of these components for context. Changes made here should not be assumed to replace changes in the authoritative source repositories.

## Repository role

Use this repository when you need:

- a single view of the overall RootRecord architecture;
- cross-component documentation and context;
- a sanitized public snapshot of the ecosystem;
- a place to document how the individual systems fit together.

Use the individual source repositories when you need:

- authoritative component history;
- component-specific development;
- deployment or operational changes;
- issues and pull requests belonging to a specific system.

## Security and sanitization

This public repository is intentionally sanitized.

It must not contain:

- credentials, tokens, passwords, private keys, certificates, or recovery codes;
- private infrastructure identifiers that are not intended for publication;
- production database files or other sensitive runtime state;
- compiled infrastructure binaries that are not required for the public snapshot.

Sensitive historical values were removed from the published Git history before this repository was made public.

Operational secrets and private infrastructure configuration remain outside this public repository.

## Structure

The top-level directories reflect the major areas of the RootRecord ecosystem, including:

- `1 - Servers/`
- `2 - RootRecord-Database/`
- `5 - RootRecord-Library/`

Additional directories and documentation provide cross-system context and supporting material.

## Canonical repository

This repository is the canonical **public umbrella/context repository** for the RootRecord ecosystem:

https://github.com/RootRecord-Software-Solutions/RootRecord-Ecosystem

It does not replace the underlying source repositories.

## Contribution and maintenance

Keep this repository synchronized with the intended public-facing ecosystem snapshot and documentation.

Before publishing changes:

1. Check that no credentials or private operational data are included.
2. Check that generated/runtime data is not accidentally committed.
3. Preserve the separation between this umbrella snapshot and the authoritative source repositories.
4. Update central documentation when the ecosystem structure changes.

---

**RootRecord Ecosystem — public umbrella and context repository.**
