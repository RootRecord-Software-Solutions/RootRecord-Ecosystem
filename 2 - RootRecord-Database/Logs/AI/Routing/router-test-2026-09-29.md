# Router test — route-specialist.py (2026-09-29 04:17 HST)

**Accuracy: 35/35 = 100.0%** · threshold 0.3 · log privacy check: PASS · no models run

| # | Prompt | Voice | Expected | Routed | Confidence | Matched | OK |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | What's the battery SOC on the Delta 2 right now? | bruce | rr-energy | rr-energy | 1.0 | battery, soc, delta 2 | ✅ |
| 2 | How many watts are the solar panels making? | ava | rr-energy | rr-energy | 0.833 | solar, watt* | ✅ |
| 3 | Is the River 2 Pro charging the laptop? | carly | rr-energy | rr-energy | 0.833 | river 2, charging | ✅ |
| 4 | Can we afford to run the 3b model on our power budget tonight? | bruce | rr-energy | rr-energy | 0.6 | power budget, power | ✅ |
| 5 | Any rain or high surf advisory for Hilo this weekend? | ava | rr-weather | rr-weather | 1.0 | rain*, surf, advisory | ✅ |
| 6 | Is Kīlauea erupting today? | carly | rr-weather | rr-weather | 1.0 | kilauea, erupting | ✅ |
| 7 | Was there an earthquake near Hawaiʻi last night? | ava | rr-weather | rr-weather | 0.5 | earthquake* | ✅ |
| 8 | Is that hurricane going to hit the islands? | bruce | rr-weather | rr-weather | 0.5 | hurricane* | ✅ |
| 9 | Why is the poller down again? | bruce | rr-system | rr-system | 0.7 | poller, re:liveness_question | ✅ |
| 10 | How much RAM and swap is the desk using? | bruce | rr-system | rr-system | 0.667 | ram, swap | ✅ |
| 11 | Is the NPU busy or is flm stuck? | ava | rr-system | rr-system | 1.0 | flm, npu, re:liveness_question | ✅ |
| 12 | Check whether the cloudflared tunnel service is running | bruce | rr-system | rr-system | 1.0 | service, cloudflared, tunnel, re:liveness_question | ✅ |
| 13 | Did someone leak the Telegram bot token in chat? | carly | rr-security | rr-security | 1.0 | token*, leak* | ✅ |
| 14 | How should we harden SSH on the desk? | carly | rr-security | rr-security | 0.7 | ssh, harden* | ✅ |
| 15 | Is this link a phishing scam? | ava | rr-security | rr-security | 1.0 | phishing, scam* | ✅ |
| 16 | Check the failed login attempts and firewall rules | carly | rr-security | rr-security | 1.0 | firewall, failed login* | ✅ |
| 17 | Is camera ch1 grabbing frames? | bruce | rr-cameras | rr-cameras | 1.0 | camera*, frames, re:cam_channel | ✅ |
| 18 | Did the daily timelapse render finish? | ava | rr-cameras | rr-cameras | 0.5 | timelapse* | ✅ |
| 19 | The Night Owl DVR footage looks blurry | carly | rr-cameras | rr-cameras | 1.0 | dvr, night owl, footage | ✅ |
| 20 | Give me a one-liner to find files bigger than 100 MB | bruce | rr-exec | rr-exec | 1.0 | one-liner, find files, re:imperative_opener | ✅ |
| 21 | Write a bash script that backs up the config folder | bruce | rr-exec | rr-exec | 1.0 | bash, script, write a, re:imperative_opener | ✅ |
| 22 | Convert this list to JSON | ava | rr-exec | rr-exec | 0.833 | json, convert, re:imperative_opener | ✅ |
| 23 | Compare the pros and cons of FLM versus Ollama for chat | ava | rr-reason | rr-reason | 0.8 | compare, pros and cons, versus | ✅ |
| 24 | Explain the trade-off between a single relay and two pollers | bruce | rr-reason | rr-reason | 0.767 | explain, trade-off* | ✅ |
| 25 | Should we migrate first or build new features? | carly | rr-reason | rr-reason | 0.333 | should we | ✅ |
| 26 | Ava, what do you think of the new website copy and tagline? | ava | rr-council-ava | rr-council-ava | 1.0 | ava, website copy, tagline, what do you think | ✅ |
| 27 | Draft a press release for Kīlauea Alerts branding | ava | rr-council-ava | rr-council-ava | 0.733 | brand*, press release | ✅ |
| 28 | Bruce, write the runbook and rollout plan for the upgrade | bruce | rr-council-bruce | rr-council-bruce | 0.933 | bruce, runbook, rollout | ✅ |
| 29 | Is this feasible for reliability, from an SRE view? | bruce | rr-council-bruce | rr-council-bruce | 1.0 | reliability, sre, feasib* | ✅ |
| 30 | Carly, seal this public claim before it ships | carly | rr-council-carly | rr-council-carly | 0.667 | carly, seal | ✅ |
| 31 | Draft a work order for the billing tiers | carly | rr-council-carly | rr-council-carly | 1.0 | billing, tiers, work order | ✅ |
| 32 | hey ava | ava | generic | generic | 0.167 | - | ✅ |
| 33 | good night everyone | bruce | generic | generic | 0.0 | - | ✅ |
| 34 | thanks, that helped a lot | carly | generic | generic | 0.0 | - | ✅ |
| 35 | lol | ava | generic | generic | 0.0 | - | ✅ |

## Held-out prompts (not tuned against; informational): 5/8 = 62.5%

| # | Prompt | Voice | Expected | Routed | Confidence | Matched | OK |
| --- | --- | --- | --- | --- | --- | --- | --- |
| H1 | Is it windy up at the site? | ava | rr-weather | rr-weather | 0.333 | wind* | ✅ |
| H2 | The batteries are almost dead, should we shut the NPU down? | bruce | rr-energy | rr-system | 0.633 | npu, re:liveness_question | ❌ |
| H3 | Can you check if the relay is running? | bruce | rr-system | rr-system | 0.5 | relay, re:liveness_question | ✅ |
| H4 | Is the solar panel cam showing glare? | carly | rr-cameras | rr-energy | 0.367 | solar | ❌ |
| H5 | What would it cost in power to keep a model loaded all night? | bruce | rr-energy | generic | 0.167 | - | ❌ |
| H6 | Tell me about RootMC | ava | rr-council-ava | rr-council-ava | 0.333 | rootmc | ✅ |
| H7 | How do I restart the weather poller? | bruce | rr-system | rr-system | 0.633 | poller, restart* | ✅ |
| H8 | Someone posted our master-key file, what now? | carly | rr-security | rr-security | 0.5 | master-key | ✅ |

| Expected route | Correct |
| --- | --- |
| rr-energy | 4/4 |
| rr-weather | 4/4 |
| rr-system | 4/4 |
| rr-security | 4/4 |
| rr-cameras | 3/3 |
| rr-exec | 3/3 |
| rr-reason | 3/3 |
| rr-council-ava | 2/2 |
| rr-council-bruce | 2/2 |
| rr-council-carly | 2/2 |
| generic | 4/4 |
