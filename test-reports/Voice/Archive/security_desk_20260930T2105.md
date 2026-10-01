# Security desk — 2026-09-30T21:05:00-10:00

- Firewall starts on boot: False
- SSH active: True
- TCP listeners: 162
- Established connections: 42
- Failed sign-ins, last hour: 0
- Failed sign-ins, last 24 hours: 0

## Spoken

Security desk at nine oh five p.m. The firewall is not set to start on boot. OpenSSH service is active. 162 TCP listeners. 42 established connections. Failed sign-ins: 0 in the last hour, 0 in the last twenty four hours.

_Source: Pacific System/scripts/host_desks.py (ufw.conf, systemctl is-active, /proc/net/tcp, auth.log counts only)._

_Template report; measured values only._
