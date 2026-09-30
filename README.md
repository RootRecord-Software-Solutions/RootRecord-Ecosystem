# RootRecord Ecosystem

> **The canonical public umbrella and context repository for the RootRecord ecosystem.**

[![Repository](https://img.shields.io/badge/repository-public-brightgreen)](https://github.com/RootRecord-Software-Solutions/RootRecord-Ecosystem)

RootRecord-Ecosystem is a **flattened, sanitized snapshot of the broader RootRecord software and infrastructure ecosystem**.

It exists so the ecosystem can be viewed as a whole: servers, databases, communications, cloud/web components, libraries, documentation, and supporting infrastructure can be inspected together without requiring every component to be understood in isolation.

A new agent with no prior chat should start at [HANDOFF.md](5%20-%20RootRecord-Library/Documentation/01-operations/HANDOFF.md), then the [system map](5%20-%20RootRecord-Library/Documentation/00-architecture/SYSTEM-MAP.md). On the live Pacific desk, run `bash verify.sh` before changing runtime. Generated telemetry under `2 - RootRecord-Database/System/status/` is not source and is not auto-committed.

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

This desk checkout is **one Git repository**. The git root is `/home/rootrecord/RootRecord-Ecosystem`. Pacific, Database, Library, and the other top-level folders are directories in that tree. They are not nested Git repositories. Do not add nested `.git` directories or gitlinks back into this snapshot.

GitHub still has separate source repositories for those domains. Those remotes keep their own histories and remain the domain homes. This public repository is the context snapshot. Automated sync also publishes Pacific, Database, and Library from this same tree.

> On this desk, edit the file where it lives in this tree. Do not assume a subdirectory has its own `origin`.

---

## Ecosystem components

The current snapshot includes material from the major RootRecord areas:

| Area | Role |
| --- | --- |
| `0 - Master-Prompt/` | Cross-project operating rules, prompt files, and live-state notes |
| `1 - Servers/` | Pacific runtime and the US Mainland continuity tree |
| `2 - RootRecord-Database/` | Persistent data, telemetry, logs, and media layout |
| `3 - RootRecord-Website/` | Removed 2026-09-30. No local website. Do not start one |
| `4 - RootRecord-Node/` | Node-related snapshot material |
| `5 - RootRecord-Library/` | Architecture, guides, agent context, and work orders |
| `6 - Android Development/` | Android application trees |
| `7 - Client Projects/` | Client-project snapshot material |

---

## Source repositories

The principal source repositories represented by this snapshot are:

- **RootRecord-Library**
- **RootRecord-Database**
- **RootRecord-Pacific-Solar-Server**
- **RootRecord-US-Mainland-Server**
- **RootRecord-Cloud**

Those GitHub repositories keep their own histories. In this checkout they are folders, not separate clones. The desk sync catalog does not push `pacific`, `database`, or `library` as their own repositories.

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

GitHub source repositories may still exist on their own. This tree does not embed them as submodules.

That makes the public snapshot straightforward to inspect and gives repository-aware tools a single coherent tree.

Treat this repository as the **public context snapshot and this desk's git root**. Domain meaning still belongs to Library (knowledge), Pacific Solar Server (runtime), and Database (persistence).

---

## Maintenance workflow

On this desk, `github_sync_all` publishes `ecosystem`, `pacific`, `database`, and `library` to `RootRecord-Software-Solutions`.

1. Edit the file in this tree.
2. Let the Pacific GitHub sync fetch, merge, and push `main`. Do not force-push.
3. Live telemetry, databases, logs, and worklogs listed in `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Github/scripts/ecosystem-skip-autocommit.txt` stay on disk and are not auto-committed.
4. Review changes for secrets, credentials, private infrastructure identifiers, and runtime artifacts before they are pushed.
5. Update this README when the ecosystem's structure or repository model changes.

`pacific`, `database`, and `library` publish as mirror rows. Their folders stay in this tree and do not get a nested `.git`.

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
**Runtime check:** 2026-09-30 01:24 HST — Pacific poller, tunnel, River 2 Pro, cameras, weather, and GitHub sync are up from this tree. Delta 2 is dead and does not transmit. Operator decisions still open: [What's left for Alexander](5%20-%20RootRecord-Library/Documentation/01-operations/2026-09-30-whats-left-for-alexander.md).

The repository is intended to remain a clean, sanitized public representation of the broader RootRecord ecosystem.

---

## Canonical location

**RootRecord-Software-Solutions/RootRecord-Ecosystem**

https://github.com/RootRecord-Software-Solutions/RootRecord-Ecosystem

---

## Related source repositories

Use this repository for ecosystem-wide context and for the desk checkout.

GitHub source repositories, when they are still the domain remote:

- RootRecord-Library
- RootRecord-Database
- RootRecord-Pacific-Solar-Server
- RootRecord-US-Mainland-Server
- RootRecord-Cloud

---

**RootRecord Ecosystem**  
*Public umbrella, context, and sanitized snapshot.*
