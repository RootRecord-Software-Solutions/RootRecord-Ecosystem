#!/bin/bash
# Enable cutaway mode, expect mpegts on cutaway.fifo from stdin for SECONDS, then restore still.
set -euo pipefail
STILLS=/home/ubuntu/youtube-stills
FIFO="$STILLS/cutaway.fifo"
SECONDS_ON="${1:-12}"

cleanup() {
  rm -f "$STILLS/cutaway.on"
  # Bounce youtube encoder back to still (stream.js respawns).
  pkill -f "ffmpeg.*cutaway.fifo.*rtmps://" 2>/dev/null || true
  pkill -f "ffmpeg.*youtube-stills/thumb.png.*rtmps://" 2>/dev/null || true
}
trap cleanup EXIT

rm -f "$FIFO"
mkfifo "$FIFO"
chmod 600 "$FIFO"
: > "$STILLS/cutaway.on"

# Restart youtube in cutaway mode (waits on fifo until we write).
pkill -f "ffmpeg.*youtube-stills/thumb.png.*rtmps://" 2>/dev/null || true
pkill -f "ffmpeg.*cutaway.fifo.*rtmps://" 2>/dev/null || true

echo "CUTAWAY_ARMED fifo=$FIFO seconds=$SECONDS_ON"
# Feed stdin (mpegts) into fifo for SECONDS_ON; ffmpeg reader is started by stream.js.
timeout "$SECONDS_ON" cat > "$FIFO" || true
echo "CUTAWAY_FEED_DONE"
