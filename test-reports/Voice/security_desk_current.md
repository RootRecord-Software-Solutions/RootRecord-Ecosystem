# Security desk — 2026-10-02T02:29:09-10:00

- Firewall starts on boot: False
- SSH active: True
- TCP listeners: 64
- Established connections: 30
- Failed sign-ins, last hour: 0
- Failed sign-ins, last 24 hours: 0

## Spoken

Security desk. Report generated at two twenty nine a.m. The firewall is not set to start on boot. OpenSSH service is active. 64 TCP listeners. 30 established connections. Failed sign-ins: 0 in the last hour, 0 in the last twenty four hours.

_Source: Pacific System/scripts/host_desks.py (ufw.conf, systemctl is-active, /proc/net/tcp, auth.log counts only)._

_Template report; measured values only._
