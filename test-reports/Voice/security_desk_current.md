# Security desk — 2026-10-03T00:22:11-10:00

- Firewall starts on boot: False
- SSH active: True
- TCP listeners: 138
- Established connections: 21
- Failed sign-ins, last hour: 0
- Failed sign-ins, last 24 hours: 2

## Spoken

Security desk. Report generated at twelve twenty two a.m. The firewall is not set to start on boot. OpenSSH service is active. 138 TCP listeners. 21 established connections. Failed sign-ins: 0 in the last hour, 2 in the last twenty four hours.

_Source: Pacific System/scripts/host_desks.py (ufw.conf, systemctl is-active, /proc/net/tcp, auth.log counts only)._

_Template report; measured values only._
