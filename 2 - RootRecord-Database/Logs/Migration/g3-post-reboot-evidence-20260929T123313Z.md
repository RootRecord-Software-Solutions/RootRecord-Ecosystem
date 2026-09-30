# G3 post-reboot evidence — 2026-09-29 ~02:33 HST (20260929T123313Z)

Read-only checks, no changes, no restarts. The list comes from the WO-SRV "Pre-reboot checkpoint 2026-09-29"; the pre-reboot snapshot is `g3-pre-reboot-checkpoint-20260929T120755Z.md`.

Boot: 02:28:09 HST (kernel 7.0.0-34-generic). User session came up through autologin; poller active at 02:28:22.

| # | Check | Result | Evidence |
| --- | --- | --- | --- |
| 1 | Uptime / boot | PASS | boot 02:28:09 HST |
| 2 | Poller | PASS | pid 3226, one instance, NRestarts 0. Local status 02:31:16 `ENERGY status=live B2=78% B1=0%` = `delta2-last.json` 78.0 (02:29:21) / `river2pro-last.json` 0.0 (02:31:17), src = canonical `ENERGY` |
| 3 | Relay | PASS (replies still BLOCKED on models) | pid 4634, one instance. Started by boot job `council_relay` at 02:28:24 and now in the `rr-rootserver-poller.service` cgroup. The running code includes the retry fix (b3754fb). 0 × 401/Unauthorized. Log unchanged since 02:08 (stdout buffered, known) |
| 4 | BLE owner | PASS | `ava-ecoflow-ble` active, pid 3195, one instance |
| 5 | Globe | PASS | `network-globe-hawaii` active, pid 5436 (started by boot job) |
| 6 | cam_server | PASS | pid 5223, one instance |
| 7 | Weather | PASS (upstream WARN) | pid 5355, one instance (Pacific `.venv`), started 02:28:27. Base dir is canonical `WEATHER/Hawai'i`, and 39 files were written since boot (latest 02:31:03). 0 tracebacks. 8 INVALID/FAILED lines since boot are NWS upstream (HTTP 500 / HTML placeholder); the same set appeared before the reboot |
| 8 | Ollama | PASS | system unit active, pid 2476, `ollama list` = 12 models |
| 9 | cloudflared | PASS | pid 3509, one instance, a poller child |
| 10 | Auto-sync | PASS | Database: 5 commits since boot (latest b422861, 02:31:23), pushes logged. Pacific/Library/skills are clean at 31fd21e / 0891b5e / 1dcee66, with no index.lock |
| 11 | Dashboard | PASS | exactly 1 `poller-dashboard.py` (pid 5002, ptyxis-spawn scope), open 264 s at 02:32:50. Launched by the autostart entry at 02:28:24, which now points at the Pacific `open-poller-window.sh` |
| 12 | NPU | PARTIAL | `amdxdna` loaded (in-tree driver 0.7.0; DKMS amdxdna 7.0.0-rc1 is only "added", not built for this kernel). `/dev/accel/accel0` present (root:render). Kernel: firmware `amdnpu/17f0_10/npu_7.sbin` loaded, no amdxdna errors. **`xrt-smi` missing, `flm` missing** (not installed) |
| 13 | G2 duplicates | PASS | 0 processes from `~/.ollama/skills`; no `telegram-relay.js`; restored G2 files dormant |

## Needs Alexander
- Install XRT tools (`xrt-smi`) and FastFlowLM (`flm`) to validate the NPU. Decide whether the DKMS amdxdna module is needed; the in-tree driver works.
- Create the `ava-/bruce-/carly-telegram` models so the relay can reply.
- B1 (river2pro) still reads 0% while supplying ac_out 73 W and usbc 95 W: physical check.
