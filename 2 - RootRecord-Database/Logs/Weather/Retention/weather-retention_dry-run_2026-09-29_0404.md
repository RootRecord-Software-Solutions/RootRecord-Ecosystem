# Weather retention — DRY-RUN — 2026-09-29T04:04:41-10:00 (HST)

Script: Pacific `Weather/scripts/weather-retention.py` (read-only; nothing moved). Dataset: `/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Weather/Hawai'i`. Archive target: `/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Archive/Previous-Datasets/Weather-<YYYYMM>/` (move, never delete).

| Class | Rule | Kept files | Kept bytes | Would move (items / files) | Would move bytes |
| --- | --- | ---: | ---: | ---: | ---: |
| current | keep | 282 | 232091347 (221.3 MB) | 0 / 0 | 0 (0 B) |
| text_dated | keep 90 d | 255 | 14876501 (14.2 MB) | 0 / 0 | 0 (0 B) |
| imagery_dated | keep 14 d | 139 | 244386793 (233.1 MB) | 0 / 0 | 0 (0 B) |
| daily_zip | keep 90 d | 0 | 0 (0 B) | 0 / 0 | 0 (0 B) |
| reports_archived | keep 30 d | 1632 | 28208173 (26.9 MB) | 0 / 0 | 0 (0 B) |
| hurricanes | keep all | 1 | 1697 (1.7 KB) | 0 / 0 | 0 (0 B) |
| other | keep (no rule) | 2 | 1310196 (1.2 MB) | 0 / 0 | 0 (0 B) |

**Total would move:** 0 files, 0 bytes (0 B). **Would delete:** 0 (policy never deletes).
**Weather/ total:** 2313 files, 521137050 bytes (497.0 MB). **Disk free:** 201.3 GB.
**Daemon log:** 30192 bytes; rotate needed: no (10 MB x 5); existing rotations: none.
**Budget alarms:** none (Weather/ < 20 GB, free >= 50 GB).

## Notes

- Daily zips mix text and imagery; they follow the 90-day zip rule. Whether zipped imagery should leave after 14 days is an open question for Alexander.
- Oldest dated archive folder / daily zip: 2026-09-29 (windows: imagery 14 d, text and zips 90 d, reports 30 d by mtime).

