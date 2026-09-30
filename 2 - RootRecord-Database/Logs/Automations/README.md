# Logs / Automations

Live automation / poller log stream for the Pacific solar node.

## Current file

| File | Role |
|------|------|
| `automations_current.log` | Active poller + job output (tailed by `poller-watch.py`) |
| `stack_reload_current.log` | Stack reload script output (created on first reload) |
| `Archive/` | Hourly rotation for `automations_current.log`; other log classes may use daily/weekly/monthly policies |

## Desk absolute path

```text
/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Logs/Automations/automations_current.log
```

This directory is the **desk checkout** of `RootRecord-Software-Solutions/RootRecord-Database`.

## Writers / readers (Pacific)

| Component | Uses |
|------|------|
| `rr-rootserver-poller.service` / poller stdout | Should append here (or via `POLLER_LOG`) |
| `Automations/scripts/poller/poller-watch.py` | Default `POLLER_LOG` → this file |
| `open-poller-window.sh` / `run-poller.sh` | Same default |
| `stack/do-stack-reload.sh` | `stack_reload_current.log` |

Env overrides: `POLLER_LOG`, `STACK_RELOAD_LOG`.

## Archive policy

`automations_current.log` is **hourly-cut** because it is the high-volume automation/poller stream. The Pacific job `automations_log_hourly_archive` copies the current file to:

```text
Archive/automations_YYYY-MM-DD_HH00.log
```

and truncates the current file after the archive copy succeeds.

The archive is operational history; the current file remains the live tail target. Other log types retain their own rotation policies.

## Git behavior

Generated binary media is excluded from Git by the Database repository policy. Automation logs remain text and may be synchronized/archived through the normal GitHub catalog flow.

The live `automations_current.log` is git-ignored and untracked (2026-09-29, owner-approved) to stop 5 s sync churn; the hourly `Archive/automations_YYYY-MM-DD_HH00.log` is the synced copy.
