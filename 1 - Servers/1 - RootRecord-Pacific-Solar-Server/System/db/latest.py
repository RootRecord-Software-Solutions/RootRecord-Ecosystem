# ==============================================================================
# FILE: System/db/latest.py
# What this file is: first-party Pacific source. Read the SECTION banner above
# the function or list you need. Every code line ends with an # info: note.
# How to edit: change the code, then change the # info: note on that same line
# so it still says what the line does. Add a new function with the SECTION
# banner from 5 - RootRecord-Library/prompts/How-To-Read-And-Edit-Code.md.
# Kind: python
# ==============================================================================
"""Read the newest host sample from System/system.db."""  # info: docstring
from __future__ import annotations  # info: from __future__ import annotations

import sys  # info: import sys
from pathlib import Path  # info: from pathlib import Path

_SKILL = Path(__file__).resolve().parents[1]  # info: set _SKILL
if str(_SKILL / "lib") not in sys.path:  # info: if str ( _SKILL / "lib" ) not in sys . path
    sys.path.insert(0, str(_SKILL / "lib"))  # info: sys . path . insert ( 0 , str ( _SKILL / "lib" ) )
if str(_SKILL) not in sys.path:  # info: if str ( _SKILL ) not in sys . path
    sys.path.insert(0, str(_SKILL))  # info: sys . path . insert ( 0 , str ( _SKILL ) )

import paths  # noqa: E402
from db.store import connect  # noqa: E402


# ====================================================
# SECTION: function latest_host
# What it does: Newest observation from system.db in the old host-last.json shape.
# Edit this block only. Leave this banner in place and update the What-it-does line if the behavior changes.
# ====================================================
def latest_host(db_path: Path | None = None) -> dict | None:  # info: def latest_host
    path = Path(db_path) if db_path else paths.SYSTEM_DB  # info: set path
    if not path.is_file():  # info: if not path . is_file
        return None  # info: return None
    conn = connect(path)  # info: set conn
    try:  # info: try
        row = conn.execute(  # info: set row
            "SELECT observation_id, observed_at, host, source FROM observation ORDER BY observed_at DESC LIMIT 1"  # info: newest sample
        ).fetchone()  # info: fetchone
        if not row:  # info: if not row
            return None  # info: return None
        fields = {}  # info: set fields
        for metric in conn.execute(  # info: for metric in conn . execute
            "SELECT metric_key, value_num, unit, state FROM measurement WHERE observation_id=?",  # info: measurements for this observation
            (row["observation_id"],),  # info: observation id
        ):  # info: end for
            fields[metric["metric_key"]] = {  # info: fields [ metric [ "metric_key" ] ]
                "value": metric["value_num"],  # info: "value"
                "unit": metric["unit"],  # info: "unit"
                "state": metric["state"],  # info: "state"
            }  # info: end field
        return {  # info: return
            "alias": "host",  # info: "alias"
            "host": row["host"],  # info: "host"
            "at": row["observed_at"],  # info: "at"
            "source": row["source"] or "proc",  # info: "source"
            "fields": fields,  # info: "fields"
        }  # info: end return
    finally:  # info: finally
        conn.close()  # info: conn . close
