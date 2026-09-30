# Energy / EcoFlow

The established energy skill remains the implementation authority for EcoFlow controls.

## File placement

- Temporary troubleshooting scripts → `/tmp/`
- Permanent action scripts → existing energy skill action structure
- Do not leave one-off helper scripts in `/home/rootrecord/`

## BLE

Important operational facts from the 2026-09-23 handoff:

- `connect()` can return before authentication.
- `wait_connected()` is not an authentication wait.
- Actions should use the established authentication wait and serialized BLE session pattern.
- One BLE session at a time is the meaningful requirement; the heartbeat service itself does not own an active BLE connection.
- Use the existing `flock` serialization.

## Readback

A command returning successfully does not prove a physical/device change.

For a toggle to be called PASS, verify the changed state through the appropriate readback/packet evidence.

AC is special:

- inverter heartbeat packets provide the meaningful AC evidence;
- library/default AC values can appear when inverter packets have not arrived;
- `null` is not the same as `false`;
- a same-connection OFF readback can remain stale.

## Catalog

Keep the `TOGGLES` catalog/status information current after meaningful PASS results.

## Current testing boundary from the 2026-09-23 handoff

Confirmed:

- Delta 2 DC 12V — PASS
- Delta 2 USB — PASS
- Delta 2 AC — PASS, with the documented probe/readback caveat

Not yet established by that handoff:

- Delta 2 AC charging
- Delta 2 grid bypass
- Delta 2 energy backup
- River 2 Pro DC
- River 2 Pro energy backup
- River 2 Pro AC / AC always-on / X-Boost

Do not turn this historical test matrix into a current live claim without re-verifying.

## Documentation drift

The 2026-09-23 work explicitly corrected stale Starlink/pack-location tracking in energy and agent documentation.

When historical documentation conflicts with the current implementation, verify the current source before preserving or reintroducing the old rule.
