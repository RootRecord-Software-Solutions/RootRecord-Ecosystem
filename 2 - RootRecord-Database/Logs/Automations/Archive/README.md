# Automation Log Archive

This directory stores rotated automation/poller logs.

## Hourly policy

`automations_current.log` is cut **once per hour** by:

```text
Automations/scripts/archive_automations_log_hourly.sh
```

Archive names:

```text
automations_YYYY-MM-DD_HH00.log
```

The script copies the current log into the archive and then truncates the current file so `poller-watch.py` can continue tailing the same path.

A non-empty current log is archived only once per hourly run; an empty current file is left untouched.

## Retention

No automatic deletion/compaction policy is defined here yet. Archives are retained as operational history until a separate retention policy is approved.
