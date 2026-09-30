# Communications / Inbox

Local files written by `Communications/Inbox/scripts/inbox.py` from the quiet-mode relay hold.

| File | Role |
| --- | --- |
| `subscribers.json` | Private `/subscribe` and `/unsubscribe` results. No confirmation is sent. |
| `feedback.jsonl` | Copy of held rows. The Relay-Inbox files stay in place. |
| `drain-ledger.jsonl` | Keys already copied (`chat_id:message_id`). |
| `overnight-last.txt` | Late-night status text. A solar line is included only when one was passed in. |
| `reply-feedback.jsonl` | Notes appended with `feedback --note`. Old training JSONL is not imported. |

Message text here is git-ignored. This README is tracked.

Cloudflare D1 drain is not this folder. D1 sync has no Folder, so that half stays paused.
