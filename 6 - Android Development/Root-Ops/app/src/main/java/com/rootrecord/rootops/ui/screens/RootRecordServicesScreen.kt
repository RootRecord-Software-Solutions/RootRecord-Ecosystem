package com.rootrecord.rootops.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rootrecord.rootops.ops.OpsServer
import com.rootrecord.rootops.ops.ServerStatus
import com.rootrecord.rootops.ops.StackService
import com.rootrecord.rootops.ops.TunnelInfo
import com.rootrecord.rootops.ui.theme.AvaAccent
import com.rootrecord.rootops.ui.theme.AvaPanel
import com.rootrecord.rootops.ui.theme.AvaTextMuted
import com.rootrecord.rootops.ui.theme.AvaWarning
import com.rootrecord.rootops.ui.theme.DetailScaffold
import com.rootrecord.rootops.ui.theme.PanelCard
import com.rootrecord.rootops.ui.theme.StatusPill

@Composable
fun RootRecordServicesScreen(
    servers: List<OpsServer>,
    services: List<StackService>,
    tunnel: TunnelInfo?,
    onBack: () -> Unit,
) {
    DetailScaffold(title = "RootRecord", subtitle = "Pacific desk", onBack = onBack) { insets ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(insets).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                Text("RootMC", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            if (servers.isEmpty()) {
                item {
                    PanelCard {
                        Text("No host status yet", fontWeight = FontWeight.SemiBold)
                        Text("play.rootmc.net is production. ava-core on the OptiPlex is the test host.", color = AvaTextMuted)
                    }
                }
            }
            items(servers, key = { it.id }) { server ->
                PanelCard {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Text(server.name, fontWeight = FontWeight.Bold)
                            Text(server.address ?: "—", style = MaterialTheme.typography.labelSmall, color = AvaTextMuted)
                        }
                        StatusPill(statusLabel(server.status), if (server.status == ServerStatus.ONLINE) AvaAccent else AvaWarning)
                    }
                }
            }
            item {
                Text("Poller stack", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                if (tunnel?.note != null) {
                    Text(tunnel.note, style = MaterialTheme.typography.labelSmall, color = AvaTextMuted)
                }
            }
            if (services.isEmpty()) {
                item { Text("Service checks are not in this snapshot.", color = AvaTextMuted) }
            }
            items(services, key = { it.id }) { service ->
                Card(colors = CardDefaults.cardColors(containerColor = AvaPanel)) {
                    Row(
                        Modifier.fillMaxWidth().padding(12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(service.label, fontWeight = FontWeight.Bold)
                        StatusPill(if (service.up) "Up" else "Down", if (service.up) AvaAccent else AvaWarning)
                    }
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

private fun statusLabel(status: ServerStatus): String = when (status) {
    ServerStatus.ONLINE -> "Online"
    ServerStatus.OFFLINE -> "Offline"
    ServerStatus.STARTING -> "Starting"
    ServerStatus.UNKNOWN -> "Unknown"
}
