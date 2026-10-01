package com.rootrecord.rootops.ops

enum class OpsTransport {
    BLUETOOTH,
    LOCALHOST,
    CLOUDFLARE,
    DISCONNECTED,
}

interface TransportAwareOpsProvider : OpsProvider {
    val transport: OpsTransport
}