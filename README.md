# RootRecord Ecosystem

> **The canonical public umbrella and context repository for the RootRecord ecosystem.**

[![Repository](https://img.shields.io/badge/repository-public-brightgreen)](https://github.com/RootRecord-Software-Solutions/RootRecord-Ecosystem)

RootRecord-Ecosystem is a **flattened, sanitized snapshot of the broader RootRecord software and infrastructure ecosystem**.

It exists so the ecosystem can be viewed as a whole: servers, databases, communications, cloud/web components, libraries, documentation, and supporting infrastructure can be inspected together without requiring every component to be understood in isolation.

---

## Purpose

The RootRecord ecosystem is composed of multiple repositories and systems with their own histories and responsibilities.

This repository provides the **shared context layer** between them.

It is intended for:

- understanding the overall architecture;
- navigating relationships between components;
- reviewing cross-system documentation;
- giving developers and tooling a single public context surface;
- maintaining a sanitized public representation of the ecosystem.

This repository **does not replace the underlying source repositories**.

---

## Repository model

There are two distinct layers:

### 1. RootRecord-Ecosystem

This repository is the **umbrella/context layer**.

It contains flattened copies of relevant ecosystem material so the complete system can be viewed together. Its contents are maintained as a public-facing snapshot and reference surface.

### 2. Individual source repositories

The underlying repositories remain the authoritative homes for their respective components, including their Git history, development work, issues, pull requests, and operational changes.

The same file or component may therefore appear here and in its source repository. That is intentional.

> **If a component-specific change is required, make that change in the component's authoritative repository first.**

---

## Ecosystem components

The current snapshot includes material from the major RootRecord areas:

| Area | Role |
| --- | --- |
| 1 - Servers/ | Server infrastructure, communications, networking, and related services |
| 2 - RootRecord-Database/ | Database, telemetry, system state, energy data, logs, and supporting data structures |
| 5 - RootRecord-Library/ | Shared documentation, reference material, and ecosystem knowledge |
| RootRecord-Cloud | Cloud/web-facing components associated with the RootRecord server ecosystem |

The snapshot may contain additional supporting directories and documentation as the ecosystem evolves.

---

## Source repositories

The principal source repositories represented by this snapshot are:

- **RootRecord-Library**
- **RootRecord-Database**
- **RootRecord-Pacific-Solar-Server**
- **RootRecord-US-Mainland-Server**
- **RootRecord-Cloud**

These repositories retain their own Git histories and remain separate from this umbrella repository.

---

## Security boundary

This repository is **public by design** and is therefore maintained as a sanitized representation.

The public snapshot must not contain:

- passwords or authentication credentials;
- API tokens or access tokens;
- private keys or certificates;
- recovery codes;
- private account identifiers;
- sensitive tunnel or infrastructure identifiers;
- production database files containing private runtime state;
- unnecessary compiled infrastructure binaries;
- generated runtime artifacts that are not appropriate for publication.

Sensitive values that were present in the working snapshot were removed before the repository was published.

The published Git history was also rewritten so the sanitized repository does not retain those removed values in its reachable history.

### Important

Making this repository public does **not** make the underlying source repositories public.

Private operational configuration and secrets remain outside this repository.

---

## Snapshot philosophy

This repository is deliberately **flattened**.

The source repositories remain intact elsewhere, including their own Git directories and histories. The umbrella repository does not attempt to create nested Git repositories or submodules for those components.

That makes the public snapshot straightforward to inspect and gives repository-aware tools a single coherent tree.

The tradeoff is that this repository should be treated as a **context snapshot**, rather than a replacement for component-level version control.

---

## Maintenance workflow

When updating the ecosystem:

1. Make component-specific changes in the authoritative source repository.
2. Update the ecosystem snapshot when the public context needs to reflect those changes.
3. Review the snapshot for secrets, credentials, private infrastructure data, and runtime artifacts.
4. Verify that generated operational files have not been accidentally included.
5. Update this README when the ecosystem's structure or repository model changes.

Before publishing a snapshot, perform a security review of both the working tree and the Git history.

---

## What belongs here

Good candidates for this repository:

- architecture documentation;
- cross-system references;
- sanitized source snapshots;
- public configuration examples;
- system relationships and integration context;
- documentation explaining how the ecosystem fits together.

Poor candidates for this repository:

- production secrets;
- credentials or recovery material;
- private keys and certificates;
- live operational state;
- sensitive runtime databases;
- component-specific development that belongs in a source repository.

---

## Status

**Repository:** Public  
**Role:** Canonical RootRecord ecosystem umbrella/context repository  
**Default branch:** main

The repository is intended to remain a clean, sanitized public representation of the broader RootRecord ecosystem.

---

## Canonical location

**RootRecord-Software-Solutions/RootRecord-Ecosystem**

https://github.com/RootRecord-Software-Solutions/RootRecord-Ecosystem

---

## Related source repositories

The umbrella repository should be used for ecosystem-wide context.

For authoritative component history and development, use the corresponding source repository:

- RootRecord-Library
- RootRecord-Database
- RootRecord-Pacific-Solar-Server
- RootRecord-US-Mainland-Server
- RootRecord-Cloud

---

**RootRecord Ecosystem**  
*Public umbrella, context, and sanitized snapshot.*
