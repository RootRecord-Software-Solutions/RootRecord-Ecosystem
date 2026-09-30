# AI Inference Activity Log

**Date:** 2026-09-29  
**Scope:** Inference requests recorded by run-infer.sh on 2026-09-29 (metadata only, no prompt text).  
**Timezone:** HST  
**Status:** IN PROGRESS

---

## Timeline

03:59 — npu-flm `llama3.2:1b` for ava (caller g3-voice-ailog-test): 4641 ms, rc 0, cold start

04:22 — npu-flm `llama3.2:1b` for ava (caller sf-fix-test): 4241 ms, rc 0, cold start

04:30 — npu-flm `llama3.2:1b` for ava (caller voice_rollup): 6163 ms, rc 0, cold start

04:31 — npu-flm `llama3.2:1b` for rr-exec (caller template_fill): 7655 ms, rc 0, cold start

---

## Outcomes

- 4 requests; p50 5402 ms, max 7655 ms
- 0 Ollama fallbacks, 0 non-zero exit codes; lowest MemAvailable after a request 7281 MB

---

## Artifacts / paths

| Path or artifact | Role |
| --- | --- |
| `/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Logs/AI/Inference/inference_current.jsonl` | Inference JSONL (metadata only) |
| `System/scripts/plumbing/run-infer.sh` | Writes one line per request |

---

## Notes

- Entries marked `~` are approximate.
- Confirmed operator times are preserved as stated.
- Do not expose secrets, tokens, or full credential values in this log.

---

## Close

**Closed:** 2026-09-29 04:31 HST  
**Status:** 4 requests, 0 fallbacks, 0 non-zero exit codes.

---

## Archive note

Filename when saved:

```text
2026-09-29 AI Inference Activity Log.md
```

Examples:

```text
2026-09-26 System Reinstall Action Log.md
2026-09-27 Cloudflare Tunnel Recovery Log.md
```

Weekly archive: move closed event logs older than the current week into `Documentation/01-operations/archive/` without rewriting content.
