# Root Ops

Private Android panel for the Pacific desk. It replaced Ava Ops.

Version `0.3.0` (`versionCode` 3). Application id `com.rootrecord.rootops`.

## What it shows

The board is the live desk, not the old Electron surface:

- EcoFlow Delta 2 (B2) and River 2 Pro (B1)
- Host CPU, memory, and load on `rootrecord-software-solutions`
- NWS Hawaii state report, Kīlauea and Mauna Loa, Hawaii and global quakes
- RootMC at `play.rootmc.net`, and ava-core on the OptiPlex (listed, not probed from this desk)
- Poller stack: poller, Telegram relay, EcoFlow BLE, Hawaii globe, cameras, weather, Ollama, Cloudflare tunnel
- A-EYES camera process. Stills stay on the desk.

## Build

JDK 17 and Android SDK Platform 36. `sdk.dir` is `/home/rootrecord/.local/opt/android-sdk`.

```bash
. ~/.local/opt/rootrecord/android-build/android-env.sh
cd "6 - Android Development/Root-Ops"
./gradlew :app:assembleDebug
```

The phone reads `https://rootserver.rootrecord.cloud` (poller on `:8799`). Emulator debug builds use `http://10.0.2.2:8799` first, then the public host.

`GET /api/ops/mobile-dashboard` is the full board. Until the poller process is restarted, the app falls back to the routes that are already live: `/energy` and `/system-status.json`.

Start, stop, and RCON are not on this desk's poller, so the phone does not offer them.
