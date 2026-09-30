#!/usr/bin/env bash
# Live-desk checks. Reads processes and source files. Does not send or restart.
set -euo pipefail
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
exec python3 "$ROOT/1 - Servers/1 - RootRecord-Pacific-Solar-Server/System/scripts/verify.py"
