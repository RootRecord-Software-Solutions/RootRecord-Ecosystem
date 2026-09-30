# Operations

Operational work requires evidence.

## Live state

Separate:

- observed current state;
- documented policy;
- historical state;
- inferred state.

Use the labels:

**Confirmed / Hypothesis / Unknown / Historical**

## Rollback

For consequential changes:

1. exact-match check;
2. create backup;
3. edit;
4. verify;
5. retain rollback path.

## Services

Do not infer that a service is healthy merely because a unit exists.

Check the actual running process, logs, endpoint, or readback relevant to the claim.

## Scheduler/runtime

Do not treat a catalog/template as proof of active scheduling.

Verify:

- what the runtime loads;
- when it loads it;
- whether restart is required;
- whether a job actually fires;
- whether the action changes the intended state.

## Automated code apply (poller stack) — standing format for all future builds

**Confirmed automation — keep for every future build:**

After GitHub merges new skills code into the live tree, the desk **automatically** fully stops and restarts the poller stack.

Path:

1. `jobs.py` → `github_sync_all` (every ~300s) → `github/scripts/sync-all.sh`
2. `push-repo-once.sh` merges remote → sets `Database/GITHUB/flags/reload-poller-stack`
3. `automations/scripts/schedule-stack-reload.sh` defers ~8s, then:
   - `stop-poller-stack.sh` (unit + poller + cloudflared + poller-watch)
   - starts `rr-rootserver-poller.service` (or CLI fallback)

**Do not** tell the operator to restart the poller after a normal code push/pull.
**Do not** start a second poller, second tunnel, or parallel apply process.
**Do not** kill `ava-ecoflow-ble.service` as part of code apply (BLE owner is separate).
**Do not** replace this with a different deploy/restart pattern in future builds unless the operator explicitly changes policy.

Manual `/home/rootrecord/rootserver-poller restart` is only for explicit operator request or a hung stack outside the sync window.

## Public endpoints

Do not introduce unauthenticated mutation endpoints.

Read-only/public telemetry and authenticated/mutating operations must remain distinct.

## Handoff

Every operational handoff should include:

- changed path(s);
- verification evidence;
- rollback path where relevant;
- remaining work;
- anything intentionally not tested;
- reminder that deploy remains **push → sync → auto full stack reload** for future builds.
