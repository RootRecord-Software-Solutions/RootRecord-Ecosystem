# Reports / Generated

Machine-written reports in the exact structure of the Library operations templates
(`Documentation/01-Operations/Templates/`), filled from measured data by Pacific `Reports/template_fill.py`.

| File | Template |
| --- | --- |
| `System-Operator-Worklog-Session_current.md` | TEMPLATE System Operator Worklog — Session |
| `RootRecord-Checkpoint_current.md` | TEMPLATE RootRecord Checkpoint |
| `Event-Action-Log_current.md` | TEMPLATE Event Action Log (AI inference activity) |
| `Work-Order_current.md` | TEMPLATE Work Order (generated draft — never auto-promoted) |
| `template-fill-validation_current.json` | Validator result + draft metadata per template |
| `<name>_rejected.md` | Output that failed structure validation (the `_current` copy is left unchanged) |
| `Archive/<name>_YYYY-MM-DDTHHMM.md` | Previous `_current` copy (rotated only when the content changed) |

These files are drafts for review. The script never writes into the Library; copy by hand after review.
Design: Library `Documentation/10-AI-and-Agent-Runtime/Template-Report-Generation.md`. Job: `template_reports_daily` (off unless `RR_TEMPLATE_REPORTS=1`).
