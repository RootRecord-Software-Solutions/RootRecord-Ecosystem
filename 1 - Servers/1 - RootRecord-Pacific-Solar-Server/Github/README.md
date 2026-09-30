# Github

GitHub repository catalog and automated push/pull for the Pacific desk.

---

## Status (2026-09-29 HST) — Phase 1 LIVE

| Item | State |
| --- | --- |
| Domain | **`Github/` only** |
| Scripts | `Github/scripts/` (from G2 github skill) |
| Catalog | `Github/scripts/repos.conf` (tab-separated) |
| jobs.py | `github_setup_remotes` + `github_sync_all` → Pacific paths |
| Evidence | Poller syncs ecosystem (umbrella root) and skills; pacific/database/library are disabled because those directories are no longer separate git checkouts |
| Logs / bak | `/home/rootrecord/Database/GITHUB/` |
| Token | `/home/rootrecord/master/master-key.env` (`GITHUB_TOKEN`) — never commit |

### Catalog rows

| id | enabled | mode | notes |
| --- | --- | --- | --- |
| ecosystem | 1 | inplace | `/home/rootrecord/RootRecord-Ecosystem` → `RootRecord-Software-Solutions/RootRecord-Ecosystem`. Does not auto-commit paths in `ecosystem-skip-autocommit.txt` (live telemetry, databases, logs, worklogs). A pull reloads the poller only when Pacific runtime code changes. |
| pacific | 0 | inplace | Flattened into the umbrella. No `.git` in this directory. |
| database | 0 | inplace | Flattened into the umbrella. No `.git` in this directory. |
| library | 0 | inplace | Flattened into the umbrella. No `.git` in this directory. |
| skills | 1 | inplace | `~/.ollama/skills` → legacy Solar-Pacific remote; restore commit `1dcee66` verified intact |
| website | 0 | mirror | enable when worktree under `Database/GITHUB/worktrees/website` exists |
| mainland | 0 | inplace | enable when path is a real git clone |

### Policy

Same automation as G2; home is **`Github/`**. No parallel `github/` folder. Quote paths with spaces in jobs.

---

*Updated 2026-09-29 HST — public umbrella is the ecosystem git root; nested pacific/database/library checkouts are not separate repositories in this tree.*
