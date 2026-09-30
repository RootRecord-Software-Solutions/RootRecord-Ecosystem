# G3 follow-ups — 2026-09-29 02:42–02:52 HST

No service restarted, no install, no hardware actuation. Backup: `/home/rootrecord/Database/GITHUB/g3-followups.bak-20260929-024412/`.

1. **Relay log buffering — LANDED (next relay start).** `Communications/telegram/scripts/ensure-relay.sh`: `PYTHONUNBUFFERED=1 nohup python3 …council-relay.py`. The argv is unchanged, so the `^python3 .+/council-relay\.py` single-instance match still works. The running relay (pid 4634) is untouched.
2. **Stack-stop weather pattern — LANDED.** `Automations/scripts/stack/stop-poller-stack.sh`: `weather/…` → `[Ww]eather/scripts/run_poller\.py` (2 places).
   - Design check: the weather started by the `weather_poller` ON_BOOT job runs in the `rr-rootserver-poller.service` cgroup (pid 5355), so `systemctl --user stop` already stops it. The pattern now also covers a stray, manually started instance.
   - `ensure-weather-poller.sh` matches `[Ww]eather/` → no duplicates after a reload.
   - Weather and relay are ON_BOOT only, so a mid-session crash is not re-ensured until the next poller start.
3. **30 s poller stop — cause found, fix LANDED (next poller start).**
   - The SIGTERM handler exists, but `scheduler_loop` only checks `_stop` between passes. Jobs that were still due kept running after the signal (02:13:13 SIGTERM → frame_grab until 02:13:25 → `ensure_tunnel_online` respawned cloudflared and waited up to 45 s).
   - At 30 s, systemd SIGKILLed python plus the new cloudflared. This was seen at 00:52, 01:11, 01:50 and 02:13; 01:19 was clean at 13 s.
   - Fix: `run_job()` returns right away once `_stop` is set. Tested on a separate copy (not the live poller): the job ran before `_stop` and was skipped after. The unit is unchanged (TimeoutStopSec=30 is enough once this is live).
4. **Weather retention — measured, PROPOSED.** 320 MB at 02:44 (207 MB 01:54, 227 MB 02:07). Most of the jump was first fetches of `_current` GOES19 EEP imagery (overwritten in place); steady growth of dated archives ≈ 4.8 MB / 30 min ≈ 0.23 GB/day. Policy is in `Pacific/Weather/README.md` §Retention (PROPOSED).
5. **FastFlowLM prep — paste-ready block in WO-SRV** ("FastFlowLM install — paste-ready").
   - `xrt-smi` ∈ `libxrt-utils` (lemonade-team PPA, 2.25.0-4~resolute1).
   - FLM v1.0.6 `ubuntu26.04` deb, sha256 `22e6fdb6…3ad7` verified against the GitHub digest.
   - No reboot needed.
