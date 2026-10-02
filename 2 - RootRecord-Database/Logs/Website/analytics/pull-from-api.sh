#!/bin/bash
# Mirror today's analytics JSON from ml2-api onto the desk bank path.
set -eu
DIR="$(cd "$(dirname "$0")" && pwd)"
DAY="${1:-$(TZ=Pacific/Honolulu date +%Y-%m-%d)}"
mkdir -p "$DIR/daily"
URL="https://api.rootrecord.cloud/api/analytics/daily?date=${DAY}"
tmp="$(mktemp)"
if curl -fsS --max-time 20 "$URL" -o "$tmp"; then
  mv -f "$tmp" "$DIR/daily/${DAY}.json"
  echo "pulled $DAY -> $DIR/daily/${DAY}.json"
else
  rm -f "$tmp"
  echo "pull failed for $DAY" >&2
  exit 1
fi
