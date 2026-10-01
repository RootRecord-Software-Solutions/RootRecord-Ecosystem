# Logs/AI/Routing

Specialist-router output (Pacific `System/scripts/plumbing/route-specialist.py`, added 2026-09-29 HST, g3-specialists).

| File | Tracked | Content |
| --- | --- | --- |
| `routing_current.jsonl` | no (git-ignored, high churn) | One JSON line per routing decision: `ts` (HST offset), `caller`, `voice`, `specialist`, `default_used`, `confidence`, `score`, `threshold`, `runner_up`, `matched` (config keyword / rule **names** only), `prefer`, `ollama_model`, `model_verified`, `prompt_chars`, `elapsed_us`. **Never prompt or reply text.** |
| `router-test-YYYY-MM-DD.md` | yes | Accuracy table from `test-route-specialist.py` (no models run) |

Disable logging with `RR_ROUTE_LOG=0` or `--no-log`; redirect with `RR_ROUTE_LOG_FILE=`. Design doc: Library `Documentation/01-AI-and-Agent-Runtime/AI-Specialist-Models-and-Routing.md`.
