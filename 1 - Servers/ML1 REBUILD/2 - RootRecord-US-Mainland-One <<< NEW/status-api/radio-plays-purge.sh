#!/bin/bash
# Empty the radio play log. One file. No copy, no rotation, no dated archive.
set -u

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
if [[ -z "${RADIO_PLAY_LOG:-}" ]]; then
  if [[ -d "$SCRIPT_DIR/../rootrecord-radio" ]]; then
    RADIO_PLAY_LOG="$(cd "$SCRIPT_DIR/../rootrecord-radio" && pwd)/plays.log"
  else
    RADIO_PLAY_LOG="/home/ubuntu/rootrecord-radio/plays.log"
  fi
fi
LOG="$RADIO_PLAY_LOG"
dir="$(dirname "$LOG")"
mkdir -p "$dir"

find "$dir" -maxdepth 1 -type f \( \
  -name 'plays.log.*' -o \
  -name 'plays.log-*' -o \
  -name 'plays.log.gz' -o \
  -name 'plays.log.old' \
\) -delete

: > "$LOG"
