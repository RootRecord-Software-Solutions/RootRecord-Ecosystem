#!/bin/bash
# Pull the Pacific current snapshot over the loopback reverse tunnel.
# The destination holds this one file. A download over 1 MiB is discarded.
set -euo pipefail
DEST=/home/ubuntu/rebroadcast
MAX=1048576
KEY=/home/ubuntu/.ssh/pacific_fetch_ed25519
KNOWN=/home/ubuntu/.ssh/pacific_fetch_known_hosts
mkdir -p "$DEST"
tmp=$(mktemp "$DEST/.hawaii-current.XXXXXX")
cleanup() { rm -f "$tmp"; }
trap cleanup EXIT
ssh -p 17022 -i "$KEY" -o BatchMode=yes -o ConnectTimeout=8 -o IdentitiesOnly=yes \
  -o StrictHostKeyChecking=yes -o UserKnownHostsFile="$KNOWN" \
  rootrecord@127.0.0.1 cat > "$tmp"
bytes=$(wc -c < "$tmp")
if [[ "$bytes" -gt "$MAX" ]]; then
  echo "fetch-pacific: reject ${bytes} bytes"
  exit 1
fi
mv -f "$tmp" "$DEST/hawaii-current.ndjson"
trap - EXIT
find "$DEST" -type f ! -name 'hawaii-current.ndjson' ! -name 'aws-current.ndjson' ! -name 'aws-current.ndjson.tmp' ! -name 'fetch-pacific.sh' -delete
echo "fetch-pacific: ${bytes} bytes"
