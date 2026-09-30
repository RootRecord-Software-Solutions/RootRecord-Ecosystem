# Completed work orders

**Standing archive for CLOSED / COMPLETE work orders.**

When a WO is fully done (all acceptance criteria met, no further phases):

1. Set **Status** to `COMPLETE` or `CLOSED` in the file header.
2. `git mv` the file into this folder:

```text
Documentation/06-development/Work-Orders/Complete/
```

3. Remove or strike it from the **active** tables in `../README.md` (optionally keep a one-line pointer under a “Recently completed” section).
4. Leave companion action-plan files with the WO, or move them together.

## Rules

- **Do not** move OPEN or IN PROGRESS WOs here.
- **Do not** rewrite content on archive — move only.
- Prefer `git mv` so history stays traceable.
- Multi-phase WOs (e.g. WO-RPT-001 with C/D still open) stay in the parent folder until the whole WO is closed.
- Human operator session logs still archive under `Documentation/01-operations/archive/YYYY-Www/` (WO-ARCH) — that path is for logs, not WOs.

## Archived 2026-09-29 ~21:41 HST

- [WO-MAP-2026-09-27](./MasterPrompt_RepoMap_Work_Order_WO-MAP-2026-09-27.md) — ownership map written and linked.
- [WO-CF-2026-09-27](./Cloudflare_Tunnel_Recovery_Work_Order_WO-CF-2026-09-27.md) — tunnel ready and public URL HTTP 200.
- [WO-ECO-001 action plan](./WO-ECO-001-Action-Plan.md) — Phase 1 reads only. Parent stays active for actuating actions.

## Created

Operator desk path (2026-09-28):

```text
/home/rootrecord/RootRecord-Ecosystem/5 - RootRecord-Library/Documentation/06-development/Work-Orders/Complete
```

*Standing convention 2026-09-28 HST.*
