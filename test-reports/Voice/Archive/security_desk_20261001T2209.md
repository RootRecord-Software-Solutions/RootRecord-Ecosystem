# Security desk — 2026-10-01T22:09:24-10:00

- Firewall starts on boot: False
- SSH active: True
- TCP listeners: 442
- Established connections: 32
- Failed sign-ins, last hour: 0
- Failed sign-ins, last 24 hours: 0

## Spoken

Security desk. Report generated at ten oh nine p.m. The firewall is not set to start on boot. OpenSSH service is active. 442 TCP listeners. 32 established connections. Failed sign-ins: 0 in the last hour, 0 in the last twenty four hours.

_Source: Pacific System/scripts/host_desks.py (ufw.conf, systemctl is-active, /proc/net/tcp, auth.log counts only)._

_Template report; measured values only._
