# Router test — route-specialist.py (2026-09-29 05:09 HST)

**Accuracy: 53/53 = 100.0%** · threshold 0.3 · log privacy check: PASS · no models run

| # | Prompt | Voice | Expected | Routed | Confidence | Matched | OK |
| --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | What's the battery SOC on the Delta 2 right now? | bruce | rr-energy | rr-energy | 1.0 | battery, soc, delta 2 | ✅ |
| 2 | How many watts are the solar panels making? | ava | rr-energy | rr-energy | 1.0 | solar, watt*, panel*, watts | ✅ |
| 3 | Is the River 2 Pro charging the laptop? | carly | rr-energy | rr-energy | 0.833 | river 2, charging | ✅ |
| 4 | Can we afford to run the 3b model on our power budget tonight? | bruce | rr-energy | rr-energy | 0.6 | power budget, power | ✅ |
| 5 | Any rain or high surf advisory for Hilo this weekend? | ava | rr-weather | rr-weather | 1.0 | rain*, surf, advisory, hilo | ✅ |
| 6 | Is Kīlauea erupting today? | carly | rr-weather | rr-weather | 1.0 | kilauea, erupting | ✅ |
| 7 | Was there an earthquake near Hawaiʻi last night? | ava | rr-weather | rr-weather | 0.5 | earthquake* | ✅ |
| 8 | Is that hurricane going to hit the islands? | bruce | rr-weather | rr-weather | 0.5 | hurricane* | ✅ |
| 9 | Why is the poller down again? | bruce | rr-system | rr-system | 0.7 | poller, re:liveness_question | ✅ |
| 10 | How much RAM and swap is the desk using? | bruce | rr-system | rr-system | 0.667 | ram, swap | ✅ |
| 11 | Is the NPU busy or is flm stuck? | ava | rr-system | rr-system | 1.0 | flm, npu, re:liveness_question | ✅ |
| 12 | Check whether the cloudflared tunnel service is running | bruce | rr-system | rr-system | 1.0 | service, cloudflared, tunnel, re:liveness_question | ✅ |
| 13 | Did someone leak the Telegram bot token in chat? | carly | rr-security | rr-security | 1.0 | token*, leak*, re:secret_in_git | ✅ |
| 14 | How should we harden SSH on the desk? | carly | rr-security | rr-security | 0.7 | ssh, harden* | ✅ |
| 15 | Is this link a phishing scam? | ava | rr-security | rr-security | 1.0 | phishing, scam* | ✅ |
| 16 | Check the failed login attempts and firewall rules | carly | rr-security | rr-security | 1.0 | firewall, failed login*, login* | ✅ |
| 17 | Is camera ch1 grabbing frames? | bruce | rr-cameras | rr-cameras | 1.0 | camera*, frames, re:cam_channel | ✅ |
| 18 | Did the daily timelapse render finish? | ava | rr-cameras | rr-cameras | 0.5 | timelapse* | ✅ |
| 19 | The Night Owl DVR footage looks blurry | carly | rr-cameras | rr-cameras | 1.0 | dvr, night owl, footage | ✅ |
| 20 | Give me a one-liner to find files bigger than 100 MB | bruce | rr-exec | rr-exec | 1.0 | one-liner, find files, re:imperative_opener | ✅ |
| 21 | Write a bash script that backs up the config folder | bruce | rr-exec | rr-exec | 1.0 | bash, script, write a, re:imperative_opener | ✅ |
| 22 | Convert this list to JSON | ava | rr-exec | rr-exec | 0.833 | json, convert, re:imperative_opener | ✅ |
| 23 | Compare the pros and cons of FLM versus Ollama for chat | ava | rr-reason | rr-reason | 0.822 | compare, pros and cons, versus | ✅ |
| 24 | Explain the trade-off between a single relay and two pollers | bruce | rr-reason | rr-reason | 0.867 | explain, trade-off* | ✅ |
| 25 | Should we migrate first or build new features? | carly | rr-reason | rr-reason | 0.333 | should we | ✅ |
| 26 | Ava, what do you think of the new website copy and tagline? | ava | rr-council-ava | rr-council-ava | 1.0 | ava, website copy, tagline, what do you think, website | ✅ |
| 27 | Draft a press release for Kīlauea Alerts branding | ava | rr-council-ava | rr-council-ava | 0.733 | brand*, press release | ✅ |
| 28 | Bruce, write the runbook and rollout plan for the upgrade | bruce | rr-council-bruce | rr-council-bruce | 0.933 | bruce, runbook, rollout | ✅ |
| 29 | Is this feasible for reliability, from an SRE view? | bruce | rr-council-bruce | rr-council-bruce | 1.0 | reliability, sre, feasib* | ✅ |
| 30 | Carly, seal this public claim before it ships | carly | rr-council-carly | rr-council-carly | 0.667 | carly, seal | ✅ |
| 31 | Draft a work order for the billing tiers | carly | rr-council-carly | rr-council-carly | 1.0 | billing, tiers, work order | ✅ |
| 32 | hey ava | ava | generic | generic | 0.167 | - | ✅ |
| 33 | good night everyone | bruce | generic | generic | 0.0 | - | ✅ |
| 34 | thanks, that helped a lot | carly | generic | generic | 0.0 | - | ✅ |
| 35 | lol | ava | generic | generic | 0.0 | - | ✅ |
| 36 | What's the PV output on the panels today? | bruce | rr-energy | rr-energy | 1.0 | panel*, pv | ✅ |
| 37 | Is the power bank charged enough for tonight? | ava | rr-energy | rr-energy | 1.0 | power, power bank, charged | ✅ |
| 38 | Heavy showers expected on Maui? | ava | rr-weather | rr-weather | 0.5 | shower*, maui | ✅ |
| 39 | What's the surf and swell looking like? | carly | rr-weather | rr-weather | 0.833 | surf, swell* | ✅ |
| 40 | Did the auto-sync push to GitHub? | bruce | rr-system | rr-system | 1.0 | auto-sync, github, sync, push* | ✅ |
| 41 | Is the internet down again? | bruce | rr-system | rr-system | 0.667 | internet, re:liveness_question | ✅ |
| 42 | Why does the tunnel keep disconnecting? | bruce | rr-system | rr-system | 0.867 | tunnel, disconnect*, re:keeps_failing | ✅ |
| 43 | Show me the latest stills from A-EYES. | ava | rr-cameras | rr-cameras | 0.943 | a-eyes, stills, re:a_still | ✅ |
| 44 | Is the camera feed still recording? | carly | rr-cameras | rr-cameras | 1.0 | camera*, feed, camera feed, recording | ✅ |
| 45 | Was a bot token pushed to the public repo? | carly | rr-security | rr-security | 0.933 | token*, re:secret_in_git | ✅ |
| 46 | Is it safe to expose port 8799? | bruce | rr-security | rr-security | 0.7 | expose*, is it safe | ✅ |
| 47 | Somebody tried to log in as root, is that an attack? | carly | rr-security | rr-security | 0.943 | attack*, log in, re:login_attempts | ✅ |
| 48 | Convert these timestamps into a markdown table. | ava | rr-exec | rr-exec | 1.0 | convert, markdown, table, re:imperative_opener, re:transform_into | ✅ |
| 49 | Exact command to tail the automations log? | bruce | rr-exec | rr-exec | 0.943 | command, exact command, tail | ✅ |
| 50 | What are the downsides of one NPU for everything? | bruce | rr-reason | rr-reason | 0.467 | downside* | ✅ |
| 51 | Walk me through whether to split the repos. | ava | rr-reason | rr-reason | 0.667 | walk me through | ✅ |
| 52 | Write website copy for the solar board launch. | ava | rr-council-ava | rr-council-ava | 0.8 | website copy, website, launch | ✅ |
| 53 | Hello there! | bruce | generic | generic | 0.0 | - | ✅ |

## Held-out prompts (not tuned against; informational): 5/8 = 62.5%

| # | Prompt | Voice | Expected | Routed | Confidence | Matched | OK |
| --- | --- | --- | --- | --- | --- | --- | --- |
| H1 | Is it windy up at the site? | ava | rr-weather | rr-weather | 0.333 | wind* | ✅ |
| H2 | The batteries are almost dead, should we shut the NPU down? | bruce | rr-energy | rr-system | 0.633 | npu, re:liveness_question | ❌ |
| H3 | Can you check if the relay is running? | bruce | rr-system | rr-system | 0.667 | relay, re:liveness_question | ✅ |
| H4 | Is the solar panel cam showing glare? | carly | rr-cameras | rr-energy | 0.867 | solar, panel* | ❌ |
| H5 | What would it cost in power to keep a model loaded all night? | bruce | rr-energy | generic | 0.1 | - | ❌ |
| H6 | Tell me about RootMC | ava | rr-council-ava | rr-council-ava | 0.333 | rootmc | ✅ |
| H7 | How do I restart the weather poller? | bruce | rr-system | rr-system | 0.633 | poller, restart* | ✅ |
| H8 | Someone posted our master-key file, what now? | carly | rr-security | rr-security | 1.0 | master-key, re:secret_in_git | ✅ |

## Held-out file `specialist-heldout-2026-09-29b.json` (never tuned against; provenance in the file's _info): 27/27 = 100.0%

| # | Prompt | Voice | Expected | Routed | Confidence | Matched | OK |
| --- | --- | --- | --- | --- | --- | --- | --- |
| X1 | How much juice is left in the big EcoFlow right now? | bruce | rr-energy | rr-energy | 0.5 | ecoflow | ✅ |
| X2 | Are the panels producing anything this morning or is it too cloudy? | ava | rr-energy | rr-energy | 0.533 | panel*, producing | ✅ |
| X3 | Will the power bank last through the night if the fridge stays plugged in? | bruce | rr-energy | rr-energy | 0.833 | power, power bank, plugged in | ✅ |
| X4 | Is it going to pour in Hilo this afternoon? | ava | rr-weather | rr-weather | 0.5 | pour*, hilo | ✅ |
| X5 | Any news from HVO about the summit today? | ava | rr-weather | rr-weather | 0.667 | hvo, summit | ✅ |
| X6 | How big were the swells on the north shore yesterday? | carly | rr-weather | rr-weather | 0.667 | swell*, north shore | ✅ |
| X7 | The desk feels sluggish, what's eating the CPU? | bruce | rr-system | rr-system | 0.667 | cpu, sluggish | ✅ |
| X8 | Did the nightly GitHub sync push everything? | bruce | rr-system | rr-system | 0.833 | github, sync, push* | ✅ |
| X9 | Is the NPU model still loaded or did it get unloaded? | bruce | rr-system | rr-system | 0.667 | npu, loaded | ✅ |
| X10 | Why does the relay keep dropping messages? | carly | rr-system | rr-system | 0.533 | relay, re:keeps_failing | ✅ |
| X11 | Check if the Starlink is online. | bruce | rr-system | rr-system | 0.767 | starlink, online, re:liveness_question | ✅ |
| X12 | Grab me a still from the driveway cam. | ava | rr-cameras | rr-cameras | 0.833 | cam, grab, re:a_still | ✅ |
| X13 | Did anything move in the yard overnight on the security feed? | carly | rr-cameras | rr-cameras | 0.633 | feed, security feed | ✅ |
| X14 | Someone keeps trying to log in over SSH from an unknown IP, should I worry? | carly | rr-security | rr-security | 0.92 | ssh, log in, unknown ip, re:login_attempts | ✅ |
| X15 | Did I accidentally commit the Discord bot key to the repo? | carly | rr-security | rr-security | 1.0 | bot key, discord bot, re:secret_in_git | ✅ |
| X16 | Is it safe to expose the dashboard to the internet? | bruce | rr-security | rr-security | 0.567 | expose*, is it safe | ✅ |
| X17 | Give me a one-line command to list the ten biggest files under Database. | bruce | rr-exec | rr-exec | 1.0 | command, one-line, re:imperative_opener | ✅ |
| X18 | Turn this list of paths into a markdown table. | ava | rr-exec | rr-exec | 1.0 | markdown, table, re:transform_into | ✅ |
| X19 | What's the exact command to tail the poller log? | bruce | rr-exec | rr-exec | 0.771 | command, exact command, tail | ✅ |
| X20 | Should I move the weather data into its own repo or keep it in Database? Walk me through it. | ava | rr-reason | rr-reason | 0.8 | should i, walk me through | ✅ |
| X21 | What are the downsides of running everything on one small PC? | bruce | rr-reason | rr-reason | 0.667 | downside* | ✅ |
| X22 | Draft a short announcement for the website about the new solar desk. | ava | rr-council-ava | rr-council-ava | 0.633 | announcement, website | ✅ |
| X23 | Write me a runbook for when the tunnel drops at night. | bruce | rr-council-bruce | rr-council-bruce | 0.367 | runbook | ✅ |
| X24 | Seal this work order before it goes out. | carly | rr-council-carly | rr-council-carly | 0.933 | seal, work order | ✅ |
| X25 | Good morning, how are you today? | ava | generic | generic | 0.0 | - | ✅ |
| X26 | Tell me a joke about coconuts. | bruce | generic | generic | 0.0 | - | ✅ |
| X27 | Thanks, that's all for now. | carly | generic | generic | 0.0 | - | ✅ |

## Held-out file `specialist-heldout-2026-09-29c-blind.json` (never tuned against; provenance in the file's _info): 21/22 = 95.5%

| # | Prompt | Voice | Expected | Routed | Confidence | Matched | OK |
| --- | --- | --- | --- | --- | --- | --- | --- |
| X1 | What percent is the River pack at? | bruce | rr-energy | rr-energy | 0.5 | pack, percent* | ✅ |
| X2 | Can we run the space heater off the batteries tonight? | carly | rr-energy | rr-energy | 0.433 | batteries | ✅ |
| X3 | How many kilowatt hours did we make yesterday? | ava | rr-energy | rr-energy | 0.5 | kilowatt* | ✅ |
| X4 | Is there a flash flood watch for the Hamakua coast? | ava | rr-weather | rr-weather | 0.333 | flood* | ✅ |
| X5 | Did Kilauea do anything overnight? | bruce | rr-weather | rr-weather | 0.5 | kilauea | ✅ |
| X6 | What's the high temperature going to be tomorrow? | carly | rr-weather | generic | 0.167 | - | ❌ |
| X7 | How much free disk is left on the desk? | bruce | rr-system | rr-system | 0.333 | disk | ✅ |
| X8 | Is the poller dashboard responding? | bruce | rr-system | rr-system | 0.833 | poller, dashboard | ✅ |
| X9 | Why did the ollama service restart at 3am? | bruce | rr-system | rr-system | 0.7 | service, restart*, ollama | ✅ |
| X10 | Pull up the timelapse from yesterday afternoon. | ava | rr-cameras | rr-cameras | 0.5 | timelapse* | ✅ |
| X11 | Is channel 2 showing anything or is it black? | carly | rr-cameras | rr-cameras | 0.5 | re:cam_channel | ✅ |
| X12 | Should I rotate the Telegram bot token? | carly | rr-security | rr-security | 0.367 | token* | ✅ |
| X13 | Who has access to the master key file? | carly | rr-security | rr-security | 0.5 | master-key | ✅ |
| X14 | Write a bash loop that renames every jpg to include the date. | bruce | rr-exec | rr-exec | 0.833 | bash, write a, re:imperative_opener | ✅ |
| X15 | Print the JSON keys in host-last.json. | bruce | rr-exec | rr-exec | 0.433 | json, re:imperative_opener | ✅ |
| X16 | Is it better to buy a second battery or more panels? | ava | rr-reason | rr-reason | 0.657 | better, re:comparison_frame | ✅ |
| X17 | Explain why single-flight matters on a box this small. | bruce | rr-reason | rr-reason | 0.467 | why, explain | ✅ |
| X18 | Give me a catchy tagline for the public status page. | ava | rr-council-ava | rr-council-ava | 0.433 | tagline | ✅ |
| X19 | Bruce, what's the rollback plan if the cutover fails? | bruce | rr-council-bruce | rr-council-bruce | 0.433 | bruce, rollback | ✅ |
| X20 | Carly, can you audit this before we publish? | carly | rr-council-carly | rr-council-carly | 0.5 | carly, audit | ✅ |
| X21 | What's your favorite color? | ava | generic | generic | 0.0 | - | ✅ |
| X22 | Aloha! | carly | generic | generic | 0.0 | - | ✅ |

## Held-out file `specialist-heldout-2026-09-29d-blind.json` (never tuned against; provenance in the file's _info): 20/21 = 95.2%

| # | Prompt | Voice | Expected | Routed | Confidence | Matched | OK |
| --- | --- | --- | --- | --- | --- | --- | --- |
| X1 | How many amps is the inverter pulling right now? | bruce | rr-energy | rr-energy | 1.0 | inverter, amps | ✅ |
| X2 | What's the pack voltage on the Delta? | bruce | rr-energy | rr-energy | 0.667 | pack, volt* | ✅ |
| X3 | Roughly how many watts does the NUC draw at idle? | ava | rr-energy | rr-energy | 0.833 | watt*, watts | ✅ |
| X4 | Would it be better to charge from the wall or wait for sun? | ava | rr-reason | rr-reason | 0.943 | better, re:comparison_frame | ✅ |
| X5 | Is it better to keep one big model or three small ones? | bruce | rr-reason | rr-reason | 1.0 | better, re:comparison_frame | ✅ |
| X6 | Which is better for the site, a hosted page or GitHub Pages? | ava | rr-reason | rr-reason | 0.886 | better, re:comparison_frame | ✅ |
| X7 | Where is the api-key for the weather feed stored? | carly | rr-security | rr-security | 0.3 | api key | ✅ |
| X8 | Did someone change the ssh key on the box? | carly | rr-security | rr-security | 0.333 | ssh | ✅ |
| X9 | Is the single flight lock stuck? | bruce | rr-system | rr-system | 0.667 | single-flight, lock | ✅ |
| X10 | Restart the network globe service for me. | bruce | rr-system | rr-system | 0.833 | service, restart*, network | ✅ |
| X11 | Any trade winds forecast for Kona this weekend? | ava | rr-weather | rr-weather | 1.0 | forecast*, wind*, kona | ✅ |
| X12 | Was there an earthquake near Pahala last night? | carly | rr-weather | rr-weather | 0.5 | earthquake* | ✅ |
| X13 | Make a time lapse of this morning from channel 1. | ava | rr-cameras | rr-cameras | 0.933 | time-lapse, re:cam_channel | ✅ |
| X14 | Why is the a eyes grabber skipping frames? | bruce | rr-cameras | rr-cameras | 0.533 | frames, a-eyes | ✅ |
| X15 | Give me a sed command to swap tabs for commas in a csv. | bruce | rr-exec | rr-exec | 0.7 | command, sed, re:imperative_opener | ✅ |
| X16 | Carly, review this billing page before it goes live. | carly | rr-council-carly | rr-council-carly | 0.933 | carly, review this, billing | ✅ |
| X17 | Ava, write a two-line tagline for RootRecord. | ava | rr-council-ava | rr-council-ava | 0.6 | ava, tagline | ✅ |
| X18 | What's the rollback if the new poller build breaks? | bruce | rr-council-bruce | rr-system | 0.367 | poller | ❌ |
| X19 | Good evening! | ava | generic | generic | 0.0 | - | ✅ |
| X20 | Who are you? | carly | generic | generic | 0.0 | - | ✅ |
| X21 | Can you sing me a song? | bruce | generic | generic | 0.0 | - | ✅ |

| Expected route | Correct |
| --- | --- |
| rr-energy | 6/6 |
| rr-weather | 6/6 |
| rr-system | 7/7 |
| rr-security | 7/7 |
| rr-cameras | 5/5 |
| rr-exec | 5/5 |
| rr-reason | 5/5 |
| rr-council-ava | 3/3 |
| rr-council-bruce | 2/2 |
| rr-council-carly | 2/2 |
| generic | 5/5 |
