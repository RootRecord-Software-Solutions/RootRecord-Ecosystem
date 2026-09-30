# AI Processing Report

Generated: 2026-09-29T04:00:10-10:00
Window: 2026-09-28T04:00-10:00 → 2026-09-29T04:00-10:00 (24 h)
Source: `Logs/AI/Inference/inference_current.jsonl` (+ `Archive/inference_YYYY-MM-DD.jsonl`)

## Summary

- Requests: 1
- NPU share (npu-flm): 1/1 = 100%
- Fallbacks to Ollama: 0
- FLM cold starts: 1
- Errors (exit_code ≠ 0): 0; empty replies: 0; unparsable lines: 0
- First / last request: 2026-09-29T03:59:35-10:00 / 2026-09-29T03:59:35-10:00

## Latency (ms, whole request incl. cold start)

- p50 4,641 · p95 4,641 · max 4,641

## Memory

- FLM peak RSS (VmHWM, host RAM only; NPU buffers not counted): max 2,007 MB
- Lowest MemAvailable seen before/after a request: 7,281 MB

## Requests by route

| Route | Requests |
|---|---|
| npu-flm | 1 |

## Requests by route / model

| Route / model | Requests |
|---|---|
| npu-flm / llama3.2:1b | 1 |

_Metadata only: prompt/reply lengths, never text._
