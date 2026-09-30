# G3 pre-reboot checkpoint — 2026-09-29 ~02:07 HST (20260929T120755Z)

Read-only snapshot. No secrets. Backup (devices.conf, jobs.py, user units): `/home/rootrecord/Database/GITHUB/g3-pre-reboot.bak-20260929-020710/`.

## Verdict: READY for reboot (with caveats below)

## Units (enabled → return after reboot)
| Unit | Scope | Enabled | Active | PID |
| --- | --- | --- | --- | --- |
| rr-rootserver-poller | user | enabled | active | 880218 (NRestarts 0) |
| ava-ecoflow-ble | user | enabled | active | 780266 |
| network-globe-hawaii | user | **static** (no [Install]) | active | 818223 — started by poller boot job `network_globe_hawaii` (ensure script runs `systemctl --user start`) |
| ollama | system | enabled | active | 2473 (12 models listed) |
| cloudflared | — | no unit | — | poller child 880225 (boot job `cloudflare_tunnel`) |
| auto-sync | — | no timer | — | poller job `github_sync_all` (EVERY_SECONDS) |

Poller ON_BOOT jobs (all enabled): self_terminal, cloudflare_tunnel, github_setup_remotes, ollama_warmup, flm_npu_warmup, council_relay (relay 880530), security_camera_server (cam_server 880600), security_timelapse_catchup, weather_poller (weather 880724), network_globe_hawaii; ONCE_AT_START ecoflow_read_boot.

User services start with the user session: `Linger=no`, GDM `AutomaticLogin=rootrecord` is enabled → they start at autologin. If autologin fails, nothing user-level starts until login (consider `loginctl enable-linger` — operator decision).

## Repos (HEAD, uncommitted, index.lock)
| Repo | HEAD | Dirty | Lock |
| --- | --- | --- | --- |
| Pacific | cd3ae63 | 0 | 0 |
| Database | 9f81b97 | 0 | 0 |
| Library | f241e2d | 0 | 0 |
| skills (G2) | 1dcee66 | 0 | 0 |

In-flight: the auto-sync runs `git fetch` continuously (normal). Shutdown SIGTERMs it (earlier restarts showed `github_sync_all FAIL code=-15` with no lock left).

## Other checks
- `devices.conf` backed up (current 01:35 version) in the folder above; pre-edit copy in `g3-defaults.bak-20260929-013505/`.
- /tmp: no boot dependency. `/tmp/ecoflow-ble.lock` is created by flock on demand; cam server logs to `/tmp/security-cam-server.log` (lost on reboot, harmless). ensure-relay / weather ensure use no /tmp state.
- Status line 02:05:59 HST B2=78% = fresh `delta2-last.json` 78.0 (02:06:41).
- Weather: 227 MB under canonical `WEATHER/` (~2 MB/min ≈ 3 GB/day); disk 208 GB free.
- NPU: `amdxdna-dkms`, `libxrt-npu2`, `libxrt2` installed; `amdxdna` loaded; `/dev/accel/accel0` present; **no `xrt-smi`** (XRT tools package not installed) and **no `flm`** binary. DKMS reported BUILD_EXCLUSIVE mismatch → verify the module loads after reboot.

## Caveats (not blockers)
- Relay retry fix (`b3754fb`) becomes active only when the relay restarts — the reboot does that.
- Weather daemon rewrites tracked `Pacific/Weather/reports/README.md` on every report cycle → Pacific commit churn (cd3ae63, ~2.8k-line diff). Needs decision (untrack + ignore, or move output).
- Poller stop takes 30 s then SIGKILL — shutdown will wait ~30 s for it.
