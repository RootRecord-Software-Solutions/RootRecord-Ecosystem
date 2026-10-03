# ==============================================================================
# FILE: Communications/Discord/lib/api.py
# Shim — canonical implementation is ML2 vendor/Discord/lib/api.py (no Pacific copy).
# ==============================================================================
"""Load Discord API helpers from the US-Mainland-Two vendor tree."""
from __future__ import annotations

import importlib.util
from pathlib import Path

_ML2_API = Path(
    "/home/rootrecord/RootRecord-Ecosystem/1 - Servers/3 - RootRecord-US-Mainland-Two"
    "/vendor/Discord/lib/api.py"
)
if not _ML2_API.is_file():
    raise ImportError(f"ML2 Discord api missing: {_ML2_API}")
_spec = importlib.util.spec_from_file_location("rr_ml2_discord_api", _ML2_API)
if _spec is None or _spec.loader is None:
    raise ImportError(f"cannot load ML2 Discord api: {_ML2_API}")
_mod = importlib.util.module_from_spec(_spec)
_spec.loader.exec_module(_mod)

# Re-export public names used by Pacific delivery scripts.
post_message = _mod.post_message
__all__ = ["post_message"]
