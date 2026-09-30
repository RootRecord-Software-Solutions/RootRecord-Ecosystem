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

## Scope

Applies especially to:

- `automations/scripts/jobs.py` (canonical)
- Other operator-edited catalogs, schedules, conf files, and long-lived scripts that humans (or AIs) extend by copy-paste

Generated one-liners and pure libraries may stay dense; **anything an operator is expected to extend must stay sectioned.**

## If layout is missing

While working on a file that *should* be sectioned and the banners/templates are gone:

1. Prefer restoring from git history of that path (last known good sectioned version).
2. Re-apply current live entries into the restored structure.
3. Do not leave the stripped form as the permanent style.
