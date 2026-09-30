# Energy / Smart-Devices (Database)

Written by Pacific `Energy/Smart-Devices/scripts/smart_devices_collect.py` (read-only collector).
Poller job `smart_devices_collect` is **gated OFF** (`RR_SMART_DEVICES=1` at poller start); files update only on manual runs until then.

| File | Contents |
| --- | --- |
| `wiz-last.json` | `{ok, at, kind:"wiz", count, state, note, devices:[{name, ip, mac, module, fw, reachable, on, dimming, temp, sceneId, rgb, rssi, state}], errors, ms}` |
| `plugs-last.json` | `{ok, at, kind:"tuya-bsd01", count, state, note, devices:[{name, ip, version, on, dps, state, missing, error}], errors, ms}` — **never contains `local_key`** |
| `collector-last.json` | summary: `{ok, at, dry_run, gate, gate_on, sources:{wiz, plugs:{state, count, ms, errors}}, files}` |

`at` is HST ISO-8601 (`-10:00`). States: PASS · FAIL · BLOCKED.
Architecture: Library `Documentation/00-architecture/Smart-Devices-Energy.md`.
