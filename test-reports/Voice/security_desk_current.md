# Security desk — 2026-09-30T16:00:50-10:00

```json
{
  "failed_1h": 0,
  "failed_24h": 0,
  "listen_tcp": 57,
  "established": 20,
  "ufw_boot": false,
  "ssh_active": true,
  "fail2ban": false
}
```

## Spoken

Security desk at four p.m. Hawaiian Standard Time. Uncomplicated Firewall is not set to start on boot. OpenSSH service is active. 57 TCP listeners. 20 established connections. Failed sign-ins: 0 in the last hour, 0 in the last twenty four hours.

_Source: Pacific System/scripts/host_desks.py (ufw.conf, systemctl is-active, /proc/net/tcp, auth.log counts only)._

_Template report; measured values only._
