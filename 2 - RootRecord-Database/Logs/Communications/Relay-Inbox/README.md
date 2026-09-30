# Logs / Communications / Relay-Inbox

Quiet-mode message hold for the Pacific council relay (`Communications/telegram/scripts/council-relay.py`).
Approved by Alexander 2026-09-29 (Library `08-ideas/2026-09-29-relay-quiet-mode-message-hold.md`).

While `RR_RELAY_REPLIES=0` (the default), the relay still consumes each Telegram update (one getUpdates owner), but it now appends the message to this folder instead of dropping it.

| File | Role |
| --- | --- |
| `relay-inbox_current.jsonl` | One JSON object per held message: `ts` (message time, HST), `received_ts`, `update_id`, `chat_id`, `chat_type`, `from` {id, username, name}, `persona_target` (`ava` / `bruce` / `carly` / `pipeline:ava>bruce>carly>ava`), `message_id`, `text`, `status` = `held` |
| `Archive/YYYY-MM-DD/relay-inbox_YYYY-MM-DD_HH00.jsonl` | Hourly cut, grouped by day. The relay checks once a minute and moves the current file when its first record is from an earlier hour |
| `replayed.jsonl` | Ledger written by the replay script (`chat_id:message_id`, time, result) so nothing is answered twice |

Files are created `0600` (folder `0700`). Silence cues (`say nothing`, …) and non-text updates are not held.

## Privacy

Everything here except this README is **git-ignored** (`/Logs/Communications/Relay-Inbox/*` in the Database `.gitignore`), because it holds private message text. Check with `git check-ignore -v Logs/Communications/Relay-Inbox/relay-inbox_current.jsonl`.

## Replay (answering held messages later)

```bash
S="/home/rootrecord/RootRecord-Ecosystem/1 - Servers/1 - RootRecord-Pacific-Solar-Server/Communications/telegram/scripts"
python3 "$S/relay-inbox-replay.py"               # list pending (read-only; default)
python3 "$S/relay-inbox-replay.py" --show-text   # include text
RR_RELAY_REPLIES=1 python3 "$S/relay-inbox-replay.py" --send --limit 5   # answer oldest 5 (sends!)
```

It sends **only** with both `--send` and `RR_RELAY_REPLIES=1`; otherwise it refuses with exit 3. Enabling sends needs Alexander's sign-off.

*Created 2026-09-29 ~04:03 HST. Takes effect at the next relay start (next poller start / `ensure-relay.sh`).*
