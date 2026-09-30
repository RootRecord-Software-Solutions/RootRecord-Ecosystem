# jobs.py additions — 2026-09-29 migration pass (exact blocks)

Pacific `Automations/scripts/jobs.py`, added 13:21–13:44 HST by the migration pass (desk agent). **Standing rule received afterwards (13:45 HST): jobs.py is not to be edited unless Alexander asks or a work order requires it.** Per that instruction these edits are **left in place, not reverted**; keeping them registered is a **sign-off item** for Alexander. Every block is gated OFF (`enabled` reads an env flag once at poller import); the running poller (PID 105444, started 03:13:28 HST) was not restarted, so none is active. The `smart_devices_collect` block between them belongs to another agent's pass and is not listed here.

Why jobs.py: the task asked to register each periodic port disabled/gated so it takes effect at the next poller start; jobs.py is the only job registry the poller reads. Alternative if Alexander prefers: remove these blocks and keep the same text in the domain READMEs as the line to add.

Original (pre-pass) copy: `/home/rootrecord/Database/GITHUB/migration-geology.bak-20260929-131652/pacific/Automations/scripts/jobs.py`.

## `geology_collect` — list `EVERY_SECONDS`, lines 282–295 (as of 13:46 HST)

```python
    {
        # Geology collector (2026-09-29, migration-geology): G1 earthquake-hourly / rr-kilauea fetch + G0 quakes.py port.
        # OFF unless RR_GEOLOGY=1 is in the poller's environment at poller start. Stdlib, 10 s per HTTP call, no delivery.
        "id": "geology_collect",
        "enabled": os.environ.get("RR_GEOLOGY", "0") == "1",
        "description": "USGS Hawaii (FDSN bbox M1+) + global M2.5+ quakes and HVO Kilauea/Mauna Loa status -> Database Geology/{Earthquakes,Volcanoes}/*-last.json + Daily/*.jsonl.",
        "interval_sec": 300,
        "builtin": "",
        "command": f'nice -n 10 python3 "{PACIFIC}/Geology/scripts/geology_collect.py" all',
        "timeout_sec": 60,
        "needs_internet": True,
        "cwd": f"{PACIFIC}/Geology",
        "env": {},
    },
```

## `geology_kilauea_cams` — list `EVERY_SECONDS`, lines 296–309 (as of 13:46 HST)

```python
    {
        # Kilauea webcam stills (2026-09-29, migration-geology): G1 kilauea/kilauea-cams port (catalog + USGS still
        # fallback; OBS push not ported). OFF unless RR_KILAUEA_CAMS=1 at poller start. Conditional GET, ~0.85 MB per change.
        "id": "geology_kilauea_cams",
        "enabled": os.environ.get("RR_KILAUEA_CAMS", "0") == "1",
        "description": "USGS HVO V1/V2/V3 Halemaumau stills -> Database Geology/Volcanoes/Cams/*-last.jpg + cams-last.json (YouTube live ids).",
        "interval_sec": 600,
        "builtin": "",
        "command": f'nice -n 10 python3 "{PACIFIC}/Geology/scripts/kilauea_cams.py"',
        "timeout_sec": 60,
        "needs_internet": True,
        "cwd": f"{PACIFIC}/Geology",
        "env": {},
    },
```

## `system_uptime_log` — list `EVERY_SECONDS`, lines 324–335 (as of 13:46 HST)

```python
    {
        # Uptime log (2026-09-29, migration-geology pass): G1 uptime-log port. OFF unless RR_UPTIME_LOG=1 at poller start.
        "id": "system_uptime_log",
        "enabled": os.environ.get("RR_UPTIME_LOG", "0") == "1",
        "description": "Desk-up/down events (origin_start, heartbeat_gap, desk_up, boot) -> Database System/uptime/ (KEEP 400).",
        "interval_sec": 60,
        "builtin": "",
        "command": f'python3 "{PACIFIC}/System/scripts/uptime_log.py" tick',
        "timeout_sec": 15,
        "cwd": f"{PACIFIC}/System",
        "env": {},
    },
```

## `voice_earthquake_report` — list `EVERY_MINUTE`, lines 399–411 (as of 13:46 HST)

```python
    {
        # Earthquake voice report (2026-09-29, migration-geology): G1 earthquake-hourly spoken script, Carly. OFF unless
        # RR_VOICE_QUAKE=1 at poller start. Reads Database Geology/Earthquakes/*-last.json (needs geology_collect). No delivery.
        "id": "voice_earthquake_report",
        "enabled": os.environ.get("RR_VOICE_QUAKE", "0") == "1",
        "description": "Carly USGS earthquake report at :08 (Hawaii first, then global) from Database Geology/. No delivery.",
        "only_at_minutes": [8],
        "builtin": "",
        "command": f'nice -n 10 python3 "{PACIFIC}/Media/Voice/scripts/voice_reports.py" earthquake_report',
        "timeout_sec": 300,
        "cwd": f"{PACIFIC}/Media/Voice/scripts",
        "env": {},
    },
```

## `voice_kilauea_report` — list `EVERY_MINUTE`, lines 412–425 (as of 13:46 HST)

```python
    {
        # Kilauea voice report (2026-09-29, old-repo migration): G1 hourly Kilauea desk line + HVO notice excerpt, Carly.
        # G1 ran it with the :02 hourly desks; :03 here so it never shares the single-flight lock with the :02 roll-ups.
        # OFF unless RR_VOICE_KILAUEA=1 at poller start. Reads Database Geology/Volcanoes (needs geology_collect). No delivery.
        "id": "voice_kilauea_report",
        "enabled": os.environ.get("RR_VOICE_KILAUEA", "0") == "1",
        "description": "Carly Kilauea report at :03 (HVO alert level, erupting state, latest notice excerpt). No delivery.",
        "only_at_minutes": [3],
        "builtin": "",
        "command": f'nice -n 10 python3 "{PACIFIC}/Media/Voice/scripts/voice_reports.py" kilauea_report',
        "timeout_sec": 300,
        "cwd": f"{PACIFIC}/Media/Voice/scripts",
        "env": {},
    },
```

## `energy_sun_times` — list `EVERY_HOUR`, lines 440–453 (as of 13:46 HST)

```python
    {
        # Sun times (2026-09-29, migration-geology pass): G1 hourly-solar-weather sun_times.py port. OFF unless
        # RR_SUN_TIMES=1 at poller start. Fetches Open-Meteo once per HST day (refresh-if-stale), else no network.
        "id": "energy_sun_times",
        "enabled": os.environ.get("RR_SUN_TIMES", "0") == "1",
        "description": "Sunrise/sunset HST (Volcano/Puna) -> Database Energy/sun/sun-times-last.json.",
        "only_at_hours": [],
        "builtin": "",
        "command": f'python3 "{PACIFIC}/Energy/scripts/sun_times.py"',
        "timeout_sec": 30,
        "needs_internet": True,
        "cwd": f"{PACIFIC}/Energy",
        "env": {},
    },
```

## `voice_hurricane_desk` — list `ON_AT`, lines 584–597 (as of 13:46 HST)

```python
    {
        # Hurricane desk voice report (2026-09-29, old-repo migration): G1 weather/hurricane-desk Hawaii block, Carly, at the
        # G1 times. OFF unless RR_VOICE_HURRICANE=1 at poller start. Reads Database Weather/Hawai'i/hurricanes/tracking
        # (weather poller) + NWS HI alerts. No delivery, no OBS/radio.
        "id": "voice_hurricane_desk",
        "enabled": os.environ.get("RR_VOICE_HURRICANE", "0") == "1",
        "description": "Carly hurricane desk (nearest tracked storm to a Hawaiian island + NWS tropical alerts). No delivery.",
        "at_times": ["05:50", "09:50", "12:50", "16:55", "20:50"],
        "builtin": "",
        "command": f'nice -n 10 python3 "{PACIFIC}/Media/Voice/scripts/voice_reports.py" hurricane_desk',
        "timeout_sec": 300,
        "cwd": f"{PACIFIC}/Media/Voice/scripts",
        "env": {},
    },
```


## Addendum 2026-09-29 ~14:12 HST

No further `jobs.py` edits after 13:45 HST (standing rule). The breadth batch added five jobs as **PROPOSED only**; they are not in `jobs.py`. They are `system_net_sample` (`RR_NET_SAMPLES`), `voice_solar_desk` (`RR_VOICE_SOLAR`), `voice_security_desk` (`RR_VOICE_SECURITY`), `voice_bandwidth_desk` (`RR_VOICE_BANDWIDTH`) and `reports_hawaii_news` (`RR_HAWAII_NEWS`). The exact blocks are in Library `Documentation/00-architecture/Pending-Job-Registrations-2026-09-29.md` §B.

## Addendum 2026-09-29 ~14:45 HST (breadth pass 2)

Still no `jobs.py` edits (standing rule; last change 13:44 HST). Five more jobs are **PROPOSED only**:

- `weather_official_hls` (`RR_OFFICIAL_HLS`, EVERY_SECONDS 600)
- `voice_official_weather` (`RR_VOICE_OFFICIAL`, EVERY_MINUTE :25)
- `weather_hurricane_global` (`RR_HURRICANE_GLOBAL`, ON_AT 05:40 / 09:40 / 12:40 / 16:40 / 20:40)
- `reports_board_catchup` (`RR_REPORT_BOARD`, ON_AT 14:00)
- `voice_boot_brief` (`RR_VOICE_BOOT`, ON_BOOT)

`reports_hawaii_news` also gained env `RR_NEWS_SEEDS_ONLY=1` and a 300 s timeout. The exact blocks and a summary table are in Library `Documentation/00-architecture/Pending-Job-Registrations-2026-09-29.md`. The test record is Library `Documentation/07-testing/2026-09-29-old-repo-ports-breadth-batch5.md`.
