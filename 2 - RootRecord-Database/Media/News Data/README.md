# Media/News Data

Live bank for the Pacific `:35` news cycle (RadioRss poll → four topic lanes → one stitched `news_update`).

```text
stories.sqlite
queue.json / queue_current.json
health_current.json
raw/  normalized/  processed/
Archive/news_data_YYYYMMDDTHHMMSS.zip   # written at :30, then live wipe
```

Canonical code: `1 - Servers/2 - RootRecord-US-Mainland-One/vendor/RadioRss/`  
Runner: `1 - Servers/1 - RootRecord-Pacific-Solar-Server/Media/News/scripts/run_news_cycle.py`
