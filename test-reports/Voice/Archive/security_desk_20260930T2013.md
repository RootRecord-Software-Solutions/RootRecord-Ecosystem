# Security desk — 2026-09-30T20:13:09-10:00

- Firewall starts on boot: False
- SSH active: True
- TCP listeners: 147
- Established connections: 50
- Failed sign-ins, last hour: 0
- Failed sign-ins, last 24 hours: 0

## Spoken

Security desk at eight thirteen p.m. The firewall is not set to start on boot. OpenSSH service is active. 147 TCP listeners. 50 established connections. Failed sign-ins: 0 in the last hour, 0 in the last twenty four hours.

_Source: Pacific System/scripts/host_desks.py (ufw.conf, systemctl is-active, /proc/net/tcp, auth.log counts only)._

_Template report; measured values only._
