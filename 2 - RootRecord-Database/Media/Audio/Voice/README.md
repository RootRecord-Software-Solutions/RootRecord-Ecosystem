# Media/Audio/Voice — single voice bank

All Ava / Bruce / Carly desk products land here:

| Path | Role |
| --- | --- |
| `<report>_current.wav` | Kokoro stitch (source for radio_push) |
| `<report>_current.ogg` | Encoded bank copy (desk / tests) |
| `<report>_current.read.txt` / `.speak.txt` | Transcripts |
| `Reports/<report>_current.md` | Text report |
| `Reports/Archive/` | Retired markdown |
| `Archive/` | Retired audio |
| `Chimes/` | Prebuilt hourly chimes |
| `Clips/` | Phrase-clip cache |

Override with `RR_VOICE_OUT_DIR` / `RR_VOICE_REPORT_OUT` only for fail-safe tests.
