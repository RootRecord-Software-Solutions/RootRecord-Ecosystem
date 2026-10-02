# System/metrics/ml2

Latest Mainland system-monitor snapshot lands as `host-last.json` (overwrite).
Pacific appends long-term samples under `Daily/YYYY-MM-DD.jsonl`.

Filled by:

- SSH: Pacific `System/scripts/rr_db_stream_receive.py`
- Telegram datapack: `Communications/telegram/scripts/datapack-pickup.py`

EcoFlow/Energy never lands here.
