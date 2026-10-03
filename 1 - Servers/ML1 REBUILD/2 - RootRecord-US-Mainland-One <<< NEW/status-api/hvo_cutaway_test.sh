#!/bin/bash
# Arm cutaway mode on ML1. Caller should push mpegts to 127.0.0.1:19001, then disarm.
set -euo pipefail
STILLS=/home/ubuntu/youtube-stills
ACTION="${1:-arm}"

case "$ACTION" in
  arm)
    : > "$STILLS/cutaway.on"
    pkill -f "ffmpeg.*youtube-stills/thumb.png.*rtmps://" 2>/dev/null || true
    pkill -f "ffmpeg.*127.0.0.1:19001.*rtmps://" 2>/dev/null || true
    echo "CUTAWAY_ARMED"
    ;;
  disarm)
    rm -f "$STILLS/cutaway.on"
    pkill -f "ffmpeg.*127.0.0.1:19001.*rtmps://" 2>/dev/null || true
    pkill -f "ffmpeg.*youtube-stills/thumb.png.*rtmps://" 2>/dev/null || true
    echo "CUTAWAY_DISARMED"
    ;;
  *)
    echo "usage: $0 arm|disarm" >&2
    exit 2
    ;;
esac
