# G2 retirement — A-Eyes cam server — 2026-09-29T10:51:03Z (2026-09-29 00:51 HST)

- **Gate:** Security/Cameras cam server PASS in `2 - RootRecord-Database/Logs/Migration/g3-runtime-evidence-20260929T101550Z.md`. Re-checked now: the `:8791` listener is Pacific (`pid 764753 cwd=<Pacific>/Security/Cameras`); `/health` = 200; the poller job `security_camera_server` runs Pacific `Security/Cameras/ensure_cam_server.sh` (last at 00:48:26 HST).
- **Dependency check** (G2 tree, systemd user and system units, crontab, rc files, Pacific tree, running processes):
  - no unit, cron or rc reference;
  - no process runs from `~/.ollama/skills`;
  - Pacific has no reference to the G2 copies.
  - The only G2 references are the dormant G2 `automations/scripts/jobs.py` (no G2 poller runs), docs (`SKILL.md`, `references/CAMERAS.md`), and `a-eyes/scripts/install_aeyes_web.sh`.
  - `install_aeyes_web.sh` is a manual one-shot installer that patches the G2 poller (`$SKILLS/automations/scripts/rootserver_poller.py`) and relaunches the G2 cam server. Nothing invokes it, and its target poller is dormant. That makes it a dormant reference, not a live one, which changes the earlier call that it blocked retirement.
- **Retired:** `~/.ollama/skills/a-eyes/scripts/cam_server.py` → Pacific `Security/Cameras/cam_server.py`; `~/.ollama/skills/a-eyes/scripts/ensure_cam_server.sh` → Pacific `Security/Cameras/ensure_cam_server.sh`. Backup: `/home/rootrecord/Database/GITHUB/g2-retire.bak-20260929-005103/`.
- **Kept:** `grab_frame.py` (imported by G2 `timelapse_engine.py`, and the timelapse row is still VERIFY PENDING); the G2 timelapse scripts; `install_aeyes_web.sh` (obsolete; do not run it — it would try to re-check out the retired files and patch the dormant G2 poller); `SKILL.md`.
