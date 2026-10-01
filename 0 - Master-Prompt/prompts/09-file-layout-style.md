# File layout style (standing — all future builds)

RootRecord operator-facing and config/source files use a **readable sectioned layout**, not a compressed minimal style.

## Required layout (canonical example: `automations/scripts/jobs.py`)

1. **Header block** — `# =====` banner with MUST-HAVE rules, paths, deploy notes.
2. **How-to / action types** — short operator instructions (copy template, enable, fields).
3. **Named sections** — each major list or concern gets:

```text
# ====================================================
# SECTION: <NAME>
# One or two lines explaining when it runs / what it is for.
# ====================================================
```

4. **Inline job comments** — before each live entry, a one-line role comment, e.g.  
   `# --- priority 1: Cloudflare tunnel ---`
5. **TEMPLATE blocks** — every editable section ends with a commented blank copy-paste job:

```text
    # --- TEMPLATE (…) — copy from here -------------------------
    # {
    #     "id": "example_…",
    #     "enabled": False,
    #     …
    # },
    # --- end TEMPLATE --------------------------------------------
```

6. **Reference appendix** when useful (e.g. FULL BLANK TEMPLATE with every key labeled; EcoFlow TOGGLES catalog).

## Do / do not

**Do**

- Preserve section banners and TEMPLATEs when editing live jobs.
- Add new jobs *above* the TEMPLATE in the matching section.
- Keep key order and quoting style consistent with neighboring jobs.
- Restore this layout if you find a file stripped to bare lists with no section headers.

**Do not**

- Compact away SECTION / TEMPLATE / HOW TO ADD blocks to “save lines.”
- Replace a sectioned config with a minimal dict-only dump unless the operator asks.
- Invent a parallel jobs file or `jobs_v2.py`.

## Scope (2026-09-30)

Every first-party Pacific `.py` and `.sh` file uses three layers:

1. A file banner.
2. A `# SECTION:` banner above each function, class, and long top-level list.
3. An `# info:` note at the end of each code line that does not already have a comment.

`Automations/scripts/jobs.py` is the canonical schedule. Each schedule list ends with a commented TEMPLATE. Copy that template to add a job. Do not delete it.

Full rules, the function copy-paste block, and the job steps: `5 - RootRecord-Library/Guides & Tutorials/How-To-Read-And-Edit-Code.md`.

Do not apply this pass to `.venv`, `vendor`, or `node_modules`.

## If layout is missing

While working on a file that *should* be sectioned and the banners/templates are gone:

1. Prefer restoring from git history of that path (last known good sectioned version).
2. Re-apply current live entries into the restored structure.
3. Do not leave the stripped form as the permanent style.
