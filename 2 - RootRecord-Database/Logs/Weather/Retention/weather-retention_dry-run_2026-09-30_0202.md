# Weather retention — DRY-RUN — 2026-09-30T02:02:46-10:00 (HST)

Script: Pacific `Weather/scripts/weather-retention.py` (read-only; nothing moved). Dataset: `/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Weather/Hawai'i`. Archive target: `/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Archive/Previous-Datasets/Weather-<YYYYMM>/` (move, never delete).

| Class | Rule | Kept files | Kept bytes | Would move (items / files) | Would move bytes |
| --- | --- | ---: | ---: | ---: | ---: |
| current | keep | 211 | 146138086 (139.4 MB) | 0 / 0 | 0 (0 B) |
| text_dated | keep 90 d | 507 | 32593987 (31.1 MB) | 0 / 0 | 0 (0 B) |
| imagery_dated | keep 14 d | 278 | 771888905 (736.1 MB) | 0 / 0 | 0 (0 B) |
| daily_zip | keep 90 d | 0 | 0 (0 B) | 0 / 0 | 0 (0 B) |
| reports_archived | keep 30 d | 2183 | 19548396 (18.6 MB) | 0 / 0 | 0 (0 B) |
| hurricanes | keep all | 1 | 2703 (2.6 KB) | 0 / 0 | 0 (0 B) |
| other | keep (no rule) | 2 | 1772519 (1.7 MB) | 0 / 0 | 0 (0 B) |

**Total would move:** 0 files, 0 bytes (0 B). **Would delete:** 0 (policy never deletes).
**Weather/ total:** 3419 files, 972496507 bytes (927.4 MB). **Disk free:** 180.3 GB.
**Daemon log:** 54510 bytes; rotate needed: no (10 MB x 5); existing rotations: none.
**Budget alarms:** none (Weather/ < 20 GB, free >= 50 GB).

## Notes

- Daily zips mix text and imagery; they follow the 90-day zip rule. Whether zipped imagery should leave after 14 days is an open question for Alexander.
- Oldest dated archive folder / daily zip: 2026-09-29 (windows: imagery 14 d, text and zips 90 d, reports 30 d by mtime).

