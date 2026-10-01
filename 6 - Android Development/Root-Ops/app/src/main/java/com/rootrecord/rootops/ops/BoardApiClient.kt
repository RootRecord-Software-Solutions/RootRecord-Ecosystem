package com.rootrecord.rootops.ops

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

/** Fetches the Root Ops board. Prefers /api/ops/mobile-dashboard, then the live poller routes. */
class BoardApiClient(
    private val baseUrls: List<String>,
    private val accessToken: String? = null,
) {
    suspend fun fetchDashboard(): RootBoardSnapshot = withContext(Dispatchers.IO) {
        var lastError: Exception? = null
        val bases = baseUrls.map { it.trim().trimEnd('/') }.filter { it.isUsableHttpBase() }
        if (bases.isEmpty()) throw IllegalStateException("no_base_url_configured")
        for (base in bases) {
            try {
                val dashboard = getJsonOrNull("$base/api/ops/mobile-dashboard")
                if (dashboard != null && (dashboard.optBoolean("ok") || dashboard.has("power") || dashboard.has("host"))) {
                    return@withContext dashboard.toBoardSnapshot()
                }
                val energy = getJsonOrNull("$base/energy")
                val system = getJsonOrNull("$base/system-status.json")
                if (energy != null || system != null) {
                    return@withContext composePollerBoard(energy, system)
                }
                lastError = IllegalStateException("poller_has_no_board")
            } catch (e: Exception) {
                lastError = e
            }
        }
        throw lastError ?: IllegalStateException("no_base_url_configured")
    }

    private fun getJsonOrNull(url: String): JSONObject? {
        val connection = (URL(url).openConnection() as HttpURLConnection).apply {
            requestMethod = "GET"
            connectTimeout = 8_000
            readTimeout = 20_000
            setRequestProperty("Accept", "application/json")
            accessToken?.takeIf { it.isNotBlank() }?.let { setRequestProperty("Authorization", "Bearer $it") }
        }
        try {
            val code = connection.responseCode
            if (code == 404) return null
            val stream = if (code in 200..299) connection.inputStream else (connection.errorStream ?: connection.inputStream)
            val payload = stream.bufferedReader().use { it.readText() }
            if (code !in 200..299) error("board_http_$code")
            return JSONObject(payload)
        } finally {
            connection.disconnect()
        }
    }
}

internal fun String.isUsableHttpBase(): Boolean {
    if (isBlank()) return false
    // Stale README host — not a RootRecord public surface; reject prefs/BuildConfig too.
    if (contains("ops.rootrecord.online", ignoreCase = true)) return false
    // Debug BuildConfig uses emulator loopback. A real phone never reaches the desk that way.
    if (contains("10.0.2.2") && !isAndroidEmulator()) return false
    return true
}

private fun isAndroidEmulator(): Boolean {
    val fingerprint = android.os.Build.FINGERPRINT.lowercase()
    val model = android.os.Build.MODEL.lowercase()
    val product = android.os.Build.PRODUCT.lowercase()
    return fingerprint.contains("generic") ||
        model.contains("emulator") ||
        model.contains("android sdk") ||
        product.contains("sdk") ||
        product.contains("emulator")
}

internal fun JSONObject.toBoardSnapshot(): RootBoardSnapshot = RootBoardSnapshot(
    ok = if (has("board_ok") && !isNull("board_ok")) optBoolean("board_ok") else optBoolean("ok", false),
    generatedAt = optString("generated_at").takeIf { it.isNotBlank() },
    weather = optJSONObject("weather")?.toWeatherInfo(),
    kilauea = optJSONObject("kilauea")?.toKilaueaInfo(),
    power = optJSONObject("power")?.toPowerInfo(),
    host = optJSONObject("host")?.toHostInfo(),
    minecraft = optJSONObject("minecraft")?.toMinecraftInfo(),
    inbox = optJSONObject("inbox")?.toInboxInfo(),
    media = optJSONObject("media")?.toMediaInfo(),
    mysql = optJSONObject("mysql")?.toMysqlInfo(),
    quakes = optJSONObject("quakes")?.toQuakesInfo(),
    reports = optJSONObject("reports")?.toReportsInfo(),
    tunnel = optJSONObject("tunnel")?.toTunnelInfo(),
    procs = optJSONObject("procs")?.toProcsInfo(),
    origin = optJSONObject("origin")?.toOriginInfo(),
    ollama = optJSONObject("ollama")?.toOllamaInfo(),
    servers = toOpsServerList(),
    services = toStackServices(),
)

private fun JSONObject.toOpsServerList(): List<OpsServer> {
    val arr = when (val raw = opt("servers")) {
        is JSONArray -> raw
        is JSONObject -> raw.optJSONArray("servers") ?: JSONArray()
        else -> JSONArray()
    }
    val out = mutableListOf<OpsServer>()
    for (i in 0 until arr.length()) {
        val item = arr.optJSONObject(i) ?: continue
        val id = item.optString("id").takeIf { it.isNotBlank() } ?: continue
        out.add(
            OpsServer(
                id = id,
                name = item.optString("name", id),
                provider = ProviderKind.ROOTRECORD,
                status = item.optString("status").toBoardServerStatus(),
                playersOnline = item.optIntOrNull("players_online"),
                playerCapacity = item.optIntOrNull("player_capacity"),
                address = item.optString("address").takeIf { it.isNotBlank() },
            ),
        )
    }
    return out
}

private fun String.toBoardServerStatus(): ServerStatus = when (lowercase()) {
    "online", "running" -> ServerStatus.ONLINE
    "offline", "stopped" -> ServerStatus.OFFLINE
    "starting", "restarting" -> ServerStatus.STARTING
    else -> ServerStatus.UNKNOWN
}

private fun JSONObject.toWeatherInfo() = WeatherInfo(
    ok = optBoolean("ok", false),
    period = optString("period").takeIf { it.isNotBlank() },
    temperatureF = optDisplayString("temperature_f"),
    forecast = optString("forecast").takeIf { it.isNotBlank() },
    alertsActive = optIntOrNull("alerts_active"),
    updatedAt = optString("updated_at").takeIf { it.isNotBlank() },
    detail = optString("detail").takeIf { it.isNotBlank() },
)

private fun JSONObject.toKilaueaInfo() = KilaueaInfo(
    ok = optBoolean("ok", false),
    alertLevel = optString("alert_level").takeIf { it.isNotBlank() },
    multiplier = optDoubleOrNull("multiplier"),
    eventsNearby = optIntOrNull("events_nearby"),
    maxMagnitude = optDoubleOrNull("max_magnitude"),
    updatedAt = optString("updated_at").takeIf { it.isNotBlank() },
    detail = optString("detail").takeIf { it.isNotBlank() },
    source = optString("source").takeIf { it.isNotBlank() },
)

private fun JSONObject.toPowerInfo(): PowerInfo {
    val devices = mutableListOf<PowerDevice>()
    optJSONArray("devices")?.let { arr ->
        for (i in 0 until arr.length()) {
            val d = arr.optJSONObject(i) ?: continue
            devices.add(
                PowerDevice(
                    label = d.optString("label", "pack"),
                    soc = d.optIntOrNull("soc"),
                    online = d.optBoolean("online", false),
                    pvW = d.optDoubleOrNull("pv_w"),
                    ebattW = d.optDoubleOrNull("ebatt_w"),
                    acOutW = d.optDoubleOrNull("ac_out_w"),
                    inputKind = d.optString("input_kind").takeIf { it.isNotBlank() },
                ),
            )
        }
    }
    return PowerInfo(
        ok = optBoolean("ok", false),
        live = optBoolean("live", false),
        source = optString("source").takeIf { it.isNotBlank() },
        batteryPct = optDoubleOrNull("battery_pct"),
        solarInW = optDoubleOrNull("solar_in_w"),
        ebattInW = optDoubleOrNull("ebatt_in_w"),
        loadW = optDoubleOrNull("load_w"),
        state = optString("state").takeIf { it.isNotBlank() },
        devices = devices,
        detail = optString("detail").takeIf { it.isNotBlank() },
        generator = if (has("generator") && !isNull("generator")) optBoolean("generator") else null,
        generatorCard = optString("generator_card").takeIf { it.isNotBlank() },
        acInW = optDoubleOrNull("ac_in_w"),
        overCeiling = if (has("over_ceiling") && !isNull("over_ceiling")) optBoolean("over_ceiling") else null,
        bleGone = if (has("ble_gone") && !isNull("ble_gone")) optBoolean("ble_gone") else null,
    )
}

private fun JSONObject.toHostInfo() = HostInfo(
    cpuPct = optDoubleOrNull("cpu_pct"),
    memPct = optDoubleOrNull("mem_pct"),
    memUsedGb = optDoubleOrNull("mem_used_gb"),
    memTotalGb = optDoubleOrNull("mem_total_gb"),
    tempC = optDoubleOrNull("temp_c"),
    hostBatteryPct = optIntOrNull("host_battery_pct"),
    diskPct = optDoubleOrNull("disk_pct"),
    gpuName = optString("gpu_name").takeIf { it.isNotBlank() },
    hostBatteryPlugged = if (has("host_battery_plugged") && !isNull("host_battery_plugged")) optBoolean("host_battery_plugged") else null,
    load1 = optDoubleOrNull("load1"),
    load5 = optDoubleOrNull("load5"),
    load15 = optDoubleOrNull("load15"),
    hostName = optString("host_name").takeIf { it.isNotBlank() },
)

private fun JSONObject.toMinecraftInfo(): MinecraftLiveInfo {
    val live = optJSONObject("live") ?: JSONObject()
    val test = optJSONObject("test") ?: JSONObject()
    return MinecraftLiveInfo(
        ok = optBoolean("ok", false),
        liveOnline = live.optBoolean("online", false),
        liveLatencyMs = live.optIntOrNull("latency_ms"),
        liveHost = live.optString("host").takeIf { it.isNotBlank() },
        livePort = live.optIntOrNull("port"),
        testOnline = test.optBoolean("online", false),
        testLatencyMs = test.optIntOrNull("latency_ms"),
        jar = optString("jar").takeIf { it.isNotBlank() },
        plugins = optIntOrNull("plugins"),
        dirPresent = optBoolean("dir_present", false) || optBoolean("dirPresent", false),
        testHost = test.optString("name").takeIf { it.isNotBlank() } ?: test.optString("host").takeIf { it.isNotBlank() },
        testDetail = test.optString("detail").takeIf { it.isNotBlank() },
        testProbed = if (test.has("probed") && !test.isNull("probed")) test.optBoolean("probed") else true,
    )
}

private fun JSONObject.toStackServices(): List<StackService> {
    val arr = optJSONArray("services") ?: return emptyList()
    val out = mutableListOf<StackService>()
    for (i in 0 until arr.length()) {
        val item = arr.optJSONObject(i) ?: continue
        val id = item.optString("id").takeIf { it.isNotBlank() } ?: continue
        out.add(StackService(id = id, label = item.optString("label", id), up = item.optBoolean("up", false)))
    }
    return out
}

private fun composePollerBoard(energy: JSONObject?, system: JSONObject?): RootBoardSnapshot {
    val power = energy?.toPollerEnergyPower()
    val host = system?.toSystemStatusHost()
    return RootBoardSnapshot(
        ok = power?.ok == true || host != null,
        generatedAt = energy?.optString("updated")?.takeIf { it.isNotBlank() }
            ?: system?.optString("generated_at")?.takeIf { it.isNotBlank() },
        power = power,
        host = host,
        servers = listOf(
            OpsServer("prod", "RootMC", ProviderKind.ROOTRECORD, ServerStatus.UNKNOWN, address = "play.rootmc.net"),
            OpsServer("test", "ava-core", ProviderKind.ROOTRECORD, ServerStatus.UNKNOWN, address = "OptiPlex"),
        ),
    )
}

private fun JSONObject.toPollerEnergyPower(): PowerInfo {
    val river = optDisplayNumber("riverSoc")
    val delta = optDisplayNumber("deltaSoc")
    val solar = optDisplayNumber("solarInW")
    val ac = optDisplayNumber("acOut")
    val devices = mutableListOf<PowerDevice>()
    if (river != null || optString("riverSoc").isNotBlank()) {
        devices.add(PowerDevice("River 2 Pro", river?.toInt(), river != null, null, null, ac, "B1"))
    }
    if (delta != null || optString("deltaSoc").isNotBlank()) {
        devices.add(PowerDevice("Delta 2", delta?.toInt(), delta != null, solar, null, ac, "B2"))
    }
    return PowerInfo(
        ok = devices.isNotEmpty(),
        live = optString("status").equals("live", ignoreCase = true),
        source = "EcoFlow samples on this desk",
        batteryPct = delta ?: river,
        solarInW = solar,
        ebattInW = null,
        loadW = ac,
        state = optString("status").takeIf { it.isNotBlank() },
        devices = devices,
        detail = optString("note").takeIf { it.isNotBlank() },
    )
}

private fun JSONObject.toSystemStatusHost(): HostInfo? {
    val metrics = optJSONObject("current")?.optJSONObject("metrics") ?: return null
    fun value(name: String): Double? = metrics.optJSONObject(name)?.optDoubleOrNull("value")
    val total = value("mem_total_bytes")
    val avail = value("mem_available_bytes")
    val usedGb = if (total != null && avail != null) (total - avail) / (1024.0 * 1024.0 * 1024.0) else null
    val totalGb = total?.div(1024.0 * 1024.0 * 1024.0)
    return HostInfo(
        cpuPct = value("cpu_percent"),
        memPct = value("mem_used_percent"),
        memUsedGb = usedGb,
        memTotalGb = totalGb,
        tempC = null,
        hostBatteryPct = null,
        diskPct = null,
        gpuName = null,
        load1 = value("load1"),
        load5 = value("load5"),
        load15 = value("load15"),
        hostName = optString("host").takeIf { it.isNotBlank() },
    )
}

private fun JSONObject.optDisplayNumber(name: String): Double? {
    val text = optDisplayString(name) ?: return null
    val cleaned = text.replace("%", "").replace("W", "", ignoreCase = true).trim()
    if (cleaned.equals("No data", ignoreCase = true) || cleaned.equals("Waiting", ignoreCase = true)) return null
    return cleaned.toDoubleOrNull()
}

private fun JSONObject.toInboxInfo() = InboxInfo(
    discordTokenSet = optBoolean("discord_token_set", false),
    reportSubscribers = optInt("report_subscribers", 0),
    inboxFile = optBoolean("inbox_file", false),
)

private fun JSONObject.toMediaFolderInfo(): MediaFolderInfo {
    val types = optJSONArray("types") ?: JSONArray()
    val names = mutableListOf<String>()
    for (i in 0 until types.length()) {
        val item = types.optJSONObject(i)
        val name = item?.optString("name")?.takeIf { it.isNotBlank() }
            ?: types.optString(i).takeIf { it.isNotBlank() }
        if (name != null) names.add(name)
    }
    return MediaFolderInfo(
        exists = optBoolean("exists", false),
        path = optString("path").takeIf { it.isNotBlank() },
        typeCount = names.size,
        typeNames = names,
    )
}

private fun JSONObject.toMediaInfo() = MediaInfo(
    public = optJSONObject("public")?.toMediaFolderInfo(),
    private = optJSONObject("private")?.toMediaFolderInfo(),
)

private fun JSONObject.toMysqlInfo() = MysqlInfo(
    live = optBoolean("live", false),
    local3306 = optBoolean("local_3306", false),
    shockbyte = optBoolean("shockbyte", false),
)

private fun JSONObject.toQuakeList(key: String): List<QuakeEvent> {
    val arr = optJSONArray(key) ?: JSONArray()
    val out = mutableListOf<QuakeEvent>()
    for (i in 0 until arr.length()) {
        val q = arr.optJSONObject(i) ?: continue
        out.add(
            QuakeEvent(
                magnitude = q.optDoubleOrNull("mag"),
                place = q.optString("place").takeIf { it.isNotBlank() },
                timeMs = q.optLongOrNull("time"),
                id = q.optString("id").takeIf { it.isNotBlank() },
                sig = q.optIntOrNull("sig"),
            ),
        )
    }
    return out
}

private fun JSONObject.toQuakesInfo() = QuakesInfo(
    global = toQuakeList("global"),
    island = toQuakeList("island"),
    fetchedAt = optString("fetched_at").takeIf { it.isNotBlank() } ?: optString("ts").takeIf { it.isNotBlank() },
)

private fun JSONObject.toReportsInfo(): ReportsInfo {
    val current = optJSONObject("current") ?: JSONObject()
    val dueArr = optJSONArray("due_today") ?: optJSONArray("dueToday")
    val due = mutableListOf<ReportWindow>()
    dueArr?.let { arr ->
        for (i in 0 until arr.length()) {
            val row = arr.optJSONObject(i) ?: continue
            due.add(
                ReportWindow(
                    id = row.optString("id"),
                    label = row.optString("label", row.optString("id")),
                    whenLabel = row.optString("when").takeIf { it.isNotBlank() },
                    done = row.optBoolean("done", false),
                    status = row.optString("status", "upcoming"),
                ),
            )
        }
    }
    val freshness = optJSONObject("freshness")
    return ReportsInfo(
        currentExists = current.optBoolean("exists", false),
        currentName = current.optString("name").takeIf { it.isNotBlank() },
        currentMtimeMs = current.optLongOrNull("mtimeMs"),
        dueToday = due,
        hstDay = optString("hstDay").takeIf { it.isNotBlank() },
        freshnessOk = freshness?.optBoolean("ok"),
    )
}

private fun JSONObject.toTunnelInfo() = TunnelInfo(
    processCount = optIntOrNull("process_count"),
    metricsOk = optBoolean("metrics_ok", false),
    connections = optIntOrNull("connections"),
    note = optString("note").takeIf { it.isNotBlank() },
)

private fun JSONObject.toProcsInfo() = ProcsInfo(
    uvicornN = optIntOrNull("uvicorn_n"),
    electronN = optIntOrNull("electron_n"),
)

private fun JSONObject.toOriginInfo() = OriginInfo(
    uptimeS = optIntOrNull("uptime_s"),
    deskUptimeS = optIntOrNull("desk_uptime_s"),
    lastReturnAt = optString("last_return_at").takeIf { it.isNotBlank() },
)

private fun JSONObject.toOllamaInfo(): OllamaInfo {
    val models = mutableListOf<String>()
    optJSONArray("models")?.let { arr ->
        for (i in 0 until arr.length()) {
            val item = arr.optJSONObject(i)
            val name = item?.optString("name")?.takeIf { it.isNotBlank() }
                ?: arr.optString(i).takeIf { it.isNotBlank() }
            if (name != null) models.add(name)
        }
    }
    return OllamaInfo(up = optBoolean("up", false), models = models)
}

private fun JSONObject.optDisplayString(name: String): String? {
    if (!has(name) || isNull(name)) return null
    return when (val raw = opt(name)) {
        is String -> raw.takeIf { it.isNotBlank() }
        is Number -> raw.toString()
        else -> optString(name).takeIf { it.isNotBlank() }
    }
}

private fun JSONObject.optIntOrNull(name: String): Int? = if (has(name) && !isNull(name)) optInt(name) else null
private fun JSONObject.optLongOrNull(name: String): Long? = if (has(name) && !isNull(name)) optLong(name) else null
private fun JSONObject.optDoubleOrNull(name: String): Double? {
    if (!has(name) || isNull(name)) return null
    val raw = opt(name) ?: return null
    return when (raw) {
        is Number -> raw.toDouble()
        is String -> raw.toDoubleOrNull()
        else -> null
    }
}
