# Poller status viewer fix — 2026-09-29 02:10–02:20 HST (20260929T121959Z)

State: **PASS**. Backup: `/home/rootrecord/Database/GITHUB/g3-viewer.bak-20260929-021233/`. No poller restart by this work.

## Cause of the flashing window (diagnosed)
1. Every Pacific pull, even docs-only, arms a stack reload (`push-repo-once.sh` → `schedule-stack-reload.sh` → `do-stack-reload.sh`). Tonight: 00:43, 00:48, 00:52, 01:19, 02:13 HST (02:13 = another agent's eb8f465 docs commits). Each reload kills `poller-watch.py` (SIGTERM, which also requests a stack stop from inside the viewer) and then opens a new window, so the window flashes closed and opens again.
2. `gnome-terminal` is not installed, so `open-poller-window.sh` used `x-terminal-emulator -e` (ptyxis) with no hold shell. Any viewer exit closes the window at once. At 02:13:49 the reopened viewer was gone within seconds; `poller-watch.py` run headless does not crash.
3. The hourly archive truncates `automations_current.log` (`: >`), and `poller-watch`'s tail keeps its old offset, so the view freezes after each hour.
4. The login autostart `~/.config/autostart/rootrecord-poller-watch.desktop` still launched the **G2** `~/.ollama/skills/automations/scripts/open-poller-window.sh`.

## Fix
- New read-only `Automations/scripts/poller/poller-dashboard.py` (Pacific 884c832). It never starts, stops or restarts anything and survives reloads (`stop-poller-stack` kills only `poller-watch.py`). It uses in-place redraw on the alternate screen and re-reads the log tail, so truncation cannot freeze it.
- `open-poller-window.sh`: single-instance guard, ptyxis `--new-window -x` branch, dashboard as the default viewer (Pacific 89a8d6d, 4a4107a).
- `do-stack-reload.sh`: the verify grep also matches the dashboard, and the comment is fixed (89a8d6d).
- Autostart `.desktop` repointed to the Pacific launcher (local file, not in git; backup in the folder above).
- Database `.gitignore`: `/Logs/Migration/*.png` (6273306).

## Verification
- 02:15:42 HST viewer relaunched → pid 938130 in its own `ptyxis-spawn` scope. At 02:19:29 it had run 226 s: 1 viewer, no new spawns in the journal, poller MainPID 933807 unchanged, NRestarts 0.
- Second `open-poller-window.sh` → "viewer already open — leaving it".
- Frame at 02:13 HST: poller, relay, BLE, cam, weather, ollama, tunnel PASS. Globe showed FAIL only during the 02:13 reload and was active again afterwards.
- Screenshot: **not possible**. GNOME Wayland refuses `gnome-screenshot` ("Unable to capture a screenshot of any window"), and no portal consent was attempted.

## Other findings
- Relay (old code) crashed again at 02:08:38 HST on a getUpdates TimeoutError. It was restarted once via `ensure-relay.sh` at ~02:12 HST: pid 930925, running the retry fix b3754fb.
- Open decision: docs-only Pacific pulls still restart the whole poller stack (sync rule). Consider skipping reloads when only `*.md` files changed (`push-repo-once.sh`), pending Alexander/WO.
- `stop-poller-stack.sh` pattern `weather/scripts/run_poller\.py` (lowercase) does not match Pacific `Weather/`, so reloads leave the weather daemon running (harmless, single instance).
