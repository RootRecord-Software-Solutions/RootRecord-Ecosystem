# Security desk — 2026-10-01T21:11:31-10:00

- Firewall starts on boot: False
- SSH active: True
- TCP listeners: 403
- Established connections: 40
- Failed sign-ins, last hour: 0
- Failed sign-ins, last 24 hours: 0

## Spoken

Security desk. Report generated at nine eleven p.m. The firewall is not set to start on boot. OpenSSH service is active. 403 TCP listeners. 40 established connections. Failed sign-ins: 0 in the last hour, 0 in the last twenty four hours.

_Source: Pacific System/scripts/host_desks.py (ufw.conf, systemctl is-active, /proc/net/tcp, auth.log counts only)._

_Template report; measured values only._
