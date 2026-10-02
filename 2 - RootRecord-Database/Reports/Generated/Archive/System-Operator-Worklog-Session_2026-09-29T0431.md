# System Operator Worklogs — Session 01

**Date:** 2026-09-29  
**Session:** Automated digest (template_fill.py)  
**Timezone:** HST  
**Window:** 01:11–04:31 HST  
**Status:** ACTIVE  
**Operator:** RootRecord

---

## Purpose

This digest covers testing of the system.

This is a manual operator worklog (not a full architecture redesign). Record what was actually done.

---

## Approximate timetable (2026-09-29 HST)

| Time (approx.) | Event |
| --- | --- |
| 01:11 | Testing record: Poller realigned to new Database root — PASS |
| 01:19 | Poller job `github_sync_all` FAIL code=-15 |
| 01:19 | Poller job `council_relay` FAIL code=127 |
| 01:49 | Testing record: Weather hook from Pacific with Weather/.venv — PASS |
| 01:49 | Poller job `github_sync_all` FAIL code=-15 |
| 02:13 | Poller job `github_sync_all` FAIL code=-15 |
| 02:15 | Testing record: Status viewer: poller-dashboard single-window launcher; docs-only pulls don't r… — PASS |
| 02:27 | Poller job `github_sync_all` FAIL code=-15 |
| 02:33 | Testing record: Post-reboot (02:28 HST boot): all services — PASS (NPU PARTIAL at the time, see next record) |
| 02:56 | Testing record: NPU / FastFlowLM install and validate — PASS |
| 03:08 | Poller job `github_sync_all` FAIL code=-15 |
| 03:09 | Testing record: Database Title-case folder rename (one stack restart) — PASS |
| 03:10 | Testing record: OOM incident: resident FLM warmup (llama3.2:3b) — FAIL → fixed; fix PASS |
| 03:10 | Poller job `security_camera_frame_grab` FAIL code=-15 |
| 03:11 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:11 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:11 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:11 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:12 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:12 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:12 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:12 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:13 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:13 | Poller job `flm_npu_warmup` FAIL code=-15 |
| 03:13 | Poller job `flm_npu_warmup` FAIL code=2 |
| 03:16 | Testing record: Laptop battery bar B3 on dashboard — LANDED / VERIFY PENDING |
| 03:20 | Testing record: EcoFlow stale data: Energy/.venv — PASS (freshness); battery levels flagged |
| 03:29 | Testing record: NPU route: llama3.2:1b on demand — PASS (route); own-session fix VERIFY PENDING |
| 03:59 | Testing record: AI inference JSONL + FLM log redaction + AI processing report — PASS; run-infer rc now 0 (kill fix); report job gated OFF |
| 04:00 | Testing record: Weather + relay supervisor (dry run) — PASS (logic); live VERIFY PENDING (next poller start) |
| 04:00 | Testing record: Pacific npu-status.sh (idle) — PASS (idle); lock-held run VERIFY PENDING |
| 04:02 | Testing record: Relay quiet-mode inbox + replay (parse only) — PASS (parse); live VERIFY PENDING (next poller start) |
| 04:04 | Testing record: Weather retention dry run — PASS (dry run: 0 to move); apply OFF pending review |
| 04:05 | Testing record: Kokoro-82M G3 port: one clip per persona — PASS (format/resources); by-ear VERIFY PENDING |
| 04:12 | Testing record: Kokoro phrase-clip cache, stitcher, QC, system_perf — PASS (68/68 QC; ASR 59/68); listen list VERIFY PENDING; job… |
| 04:14 | Testing record: Hawaiian place-name pronunciation sheet — PASS (text 99/99); by-ear VERIFY PENDING |
| 04:16 | Testing record: Specialist router unit test (no models) — PASS (35/35 labelled; held-out 5/8 informational; log priva… |
| 04:18 | Testing record: Specialists: 2 live tiny requests (Ollama rr-energy + FLM rr-weather system mes… — PASS (gate honoured; 6.3 s / 5.1 s; noth… |

---

## Completed this session

### Testing records

- [x] Poller realigned to new Database root — PASS
- [x] Weather hook from Pacific with Weather/.venv — PASS
- [x] Status viewer: poller-dashboard single-window launcher; docs-only pulls don't reload — PASS
- [x] Post-reboot (02:28 HST boot): all services — PASS (NPU PARTIAL at the time, see next record)
- [x] NPU / FastFlowLM install and validate — PASS
- [x] Database Title-case folder rename (one stack restart) — PASS
- [ ] OOM incident: resident FLM warmup (llama3.2:3b) — FAIL → fixed; fix PASS
- [ ] Laptop battery bar B3 on dashboard — LANDED / VERIFY PENDING
- [x] EcoFlow stale data: Energy/.venv — PASS (freshness); battery levels flagged
- [x] NPU route: llama3.2:1b on demand — PASS (route); own-session fix VERIFY PENDING
- [x] AI inference JSONL + FLM log redaction + AI processing report — PASS; run-infer rc now 0 (kill fix); report job gated OFF
- [x] Weather + relay supervisor (dry run) — PASS (logic); live VERIFY PENDING (next poller start)
- [x] Pacific npu-status.sh (idle) — PASS (idle); lock-held run VERIFY PENDING
- [x] Relay quiet-mode inbox + replay (parse only) — PASS (parse); live VERIFY PENDING (next poller start)
- [x] Weather retention dry run — PASS (dry run: 0 to move); apply OFF pending review
- [x] Kokoro-82M G3 port: one clip per persona — PASS (format/resources); by-ear VERIFY PENDING
- [x] Kokoro phrase-clip cache, stitcher, QC, system_perf — PASS (68/68 QC; ASR 59/68); listen list VERIFY PENDING; job gated OFF
- [x] Hawaiian place-name pronunciation sheet — PASS (text 99/99); by-ear VERIFY PENDING
- [x] Specialist router unit test (no models) — PASS (35/35 labelled; held-out 5/8 informational; log privacy PASS)
- [x] Specialists: 2 live tiny requests (Ollama rr-energy + FLM rr-weather system message) — PASS (gate honoured; 6.3 s / 5.1 s; nothing resident after)

### AI inference and poller

- [x] 3 inference requests (routes: npu-flm); p50 4641 ms, max 6163 ms
- [x] Ollama fallbacks: 0; non-zero exit codes: 0
- [ ] Poller log: 592 job runs, 18 FAIL lines

### Explicit non-goals

- Automation does not restart services, use sudo, send messages or write into the Library.
- Decisions and sign-offs stay with the operator; this digest only aggregates sources.

---

## Blockers / residual items

| Item | Notes |
| --- | --- |
| Poller restart | Needs Alexander sign-off (operator worklog) |
| sudo: `OLLAMA_KEEP_ALIVE=0` in `ollama.service` | Needs Alexander sign-off (operator worklog) |
| Hardware tests: | Needs Alexander sign-off (operator worklog) |
| Enabling voice output or Telegram sends | Needs Alexander sign-off (operator worklog) |
| Removing internal copies after the external-drive backup | Needs Alexander sign-off (operator worklog) |
| Security remediation: | Needs Alexander sign-off (operator worklog) |
| External drive `/dev/sda1` (DATABASE) — BLOCKED | Needs Alexander sign-off (operator worklog) |
| G2 retirement of any file (all KEPT). Weather repo decision (PROPOSED, not approved). | Needs Alexander sign-off (operator worklog) |
| Weather retention apply: | Needs Alexander sign-off (operator worklog) |
| Relay replay sends: | Needs Alexander sign-off (operator worklog) |

---

## Decisions (if any)

> No decisions are recorded by automation.

Rationale: decisions come from the operator; this digest only aggregates measured sources.

---

## State at session close (~04:31 HST)

- **Runtime:** poller `active`; BLE `active`; last poller log line 04:31 HST
- **Library / docs:** 20 testing records dated 2026-09-29 in the 07-testing index
- **GitHub / sync:** ok (last github_sync_all line 04:27 HST)
- **Next useful step:** Restart the poller.

**Status:** Sign-off needed: Poller restart.

---

## Archive note

Filename when saved:

```text
2026-09-29 System Operator Worklog — Session 01.md
```

Weekly archive: move closed sessions older than the current week into `Documentation/01-operations/archive/` (or dated weekly folder) without rewriting content.
