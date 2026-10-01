# 6 - Android Development

This folder holds all RootRecord Android development from 2026-09-29 on. One folder per app, in Title-case with hyphens. Release builds are in `<App>/Releases/`.

- **Not a git repo** and **not in auto-sync.** No row in Pacific `Github/scripts/repos.conf`, and Push.sh / Pull.sh don't touch it. Nothing here gets pushed.
- The `.gitignore` here covers keystores, `keystore.properties`, `local.properties`, `google-services.json`, `.env`, recovery codes, `memory/test_credentials*`, build output (`build/`, `.gradle/`, `.idea/`, `.cxx/`, `node_modules/`) and `*.apk`, `*.aab` and `Releases/`. It was checked with `git check-ignore`: 24 of 24 secret and artifact files are ignored (17 secret files, 7 release artifacts). If you `git init` an app folder later, copy these rules into it first.
- Every signing or secret file is mode **0600** and `keystore/` folders are 0700. Contents are never printed or documented.
- Import: 2026-09-29 14:30–14:45 HST. Size: 80.7 MB in 1,033 files. Budget: 40 GB.
- Full inventory: Library `Documentation/05-Products-Repositories-and-Applications/Android-Apps-Inventory.md`.

## Apps

| Folder | App (applicationId) | Version in source | Source copied from | Releases/ |
| --- | --- | --- | --- | --- |
| `Kilauea-App/` | Kīlauea Alerts (`com.rootrecord.kilauea`) | 1.0.47 (47) | desk `~/old ollama/old skills/kilauea/kilauea-alerts/android` | `RootRecord-Kilauea-Alerts-1.0.47.apk` |
| `Weather-Manager/` | Weather Manager, Capacitor (`com.rootrecord.weathermanager`) | 1.0.46 (46) | GitHub `rootrecordsoftwaresolutions/mirror-rootrecord-monorepo` @ `dc15487`, `Mobile/weather-manager-mobile` | newest artifacts found: APK from `mirror-rootrecord-mobile-development-2026` main `8a2c9e6` (2026-05-02) and `RootRecord-Weather-v1.0.9-20260429-1913.aab`. No 1.0.46 build exists anywhere |
| `RootMC-Android/` | RootMC / Block Notes (`com.rootrecord.rootmc`) | 1.0.31 (31) | desk `~/old ollama/old skills/rootmc-android/android` | `RootRecord-RootMC-1.0.32.aab` (from the 2 TB drive) and `.apk` |
| `Root-Ops/` | Root Ops (`com.rootrecord.rootops`) | 0.3.0 (3) | renamed from Ava Ops; talks to the Pacific poller | `RootRecord-Ava-Ops-0.2.0-debug.apk` (older debug build, 2026-09-16) |
| `Business-Manager/` | Business Manager, Capacitor (`com.rootrecord.businessmanager`) | 1.0.42 (42) | monorepo `Mobile/business-manager-app` | `RootRecord-BusinessManager-debug-20260429-1939.apk` (older debug build, the newest one found) |
| `Root-Goals/` | Root Goals, Capacitor (`com.rootrecord.rootgoals`) | 1.0.9 (9) | monorepo `Mobile/root-goals-mobile` | none found |
| `Root-Farms/` | Root Units / Farms, Capacitor (`com.rootrecord.rootunits`) | 1.0.9 (9) | monorepo `Mobile/root-farms-app` (a 95 MB `.exe` installer and a 2.3 MB `.mp4` were excluded; the app doesn't reference them) | none found |
| `Token-Manager/` | Token Manager, Capacitor (`com.rootrecord.tokenmanager`) | 0.1.2 (4) | monorepo `Mobile/token-manager-app` | none found |
| `Account-Hub/` | Account Hub, Capacitor (`com.rootrecord.accounthub`) | 0.1.3 (5) | monorepo `Mobile/account-hub-app` | none found |

Excluded from every copy: `build/`, `.gradle/`, `.idea/`, `.cxx/`, `node_modules/`, `__pycache__/`, `builds/` and `release/` artifact folders (only the newest artifact went to `Releases/`), older duplicate copies, SDKs and unreferenced large media.

## Before building

- The desk has no Android SDK, no Android Studio and no Java. A Gradle build wasn't attempted because it's heavy; that's on the to-do list.
- The Capacitor apps (Weather, Business, Goals, Farms, Token, Account Hub) are the native shell in `android/` plus `Web-Source/`, the React web app copied from monorepo `Web/apps/<app>-web` (without `node_modules/` or `build/`). The native assets have no built web bundle. Their `capacitor.config.json` still says `webDir: ../../Web/apps/<app>-web/build`. It was left unedited; change it to `Web-Source/build` (and the `build:web` script) before `cap sync`.
- The three `local.properties` files (Kilauea, RootMC, Root-Ops) set `sdk.dir`. They also hold the release signing values for Kilauea and RootMC, so treat them as secrets.
