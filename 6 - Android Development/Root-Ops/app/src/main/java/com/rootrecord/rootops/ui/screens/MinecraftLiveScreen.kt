package com.rootrecord.rootops.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rootrecord.rootops.ops.MinecraftLiveInfo
import com.rootrecord.rootops.ui.theme.AvaAccent
import com.rootrecord.rootops.ui.theme.AvaTextMuted
import com.rootrecord.rootops.ui.theme.AvaWarning
import com.rootrecord.rootops.ui.theme.DetailScaffold
import com.rootrecord.rootops.ui.theme.InfoRow
import com.rootrecord.rootops.ui.theme.PanelCard
import com.rootrecord.rootops.ui.theme.StatusPill
import com.rootrecord.rootops.ui.theme.WaitingForLive

@Composable
fun MinecraftLiveScreen(minecraft: MinecraftLiveInfo?, onBack: () -> Unit, onRefresh: () -> Unit = {}) {
    DetailScaffold(title = "Minecraft Live", onBack = onBack) { insets ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(insets).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            if (minecraft == null) {
                item { WaitingForLive("Minecraft status is not in the current board snapshot.") }
            } else {
                item {
                    PanelCard {
                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Icon(Icons.Outlined.Person, contentDescription = null, tint = AvaTextMuted)
                                Text("play.rootmc.net", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium)
                            }
                            StatusPill(if (minecraft.liveOnline) "Online" else "Down", if (minecraft.liveOnline) AvaAccent else AvaWarning)
                        }
                        InfoRow("Host", minecraft.liveHost ?: "—")
                        InfoRow("Port", minecraft.livePort?.toString() ?: "—")
                        InfoRow("Latency", minecraft.liveLatencyMs?.let { "${it} ms" } ?: "—")
                    }
                }
                item {
                    PanelCard {
                        Text(minecraft.testHost ?: "ava-core", fontWeight = FontWeight.Bold)
                        Text("OptiPlex test host", style = MaterialTheme.typography.labelSmall, color = AvaTextMuted)
                        InfoRow(
                            "Status",
                            when {
                                !minecraft.testProbed -> "Not probed from this desk"
                                minecraft.testOnline -> "Online"
                                else -> "Down"
                            },
                        )
                        InfoRow("Latency", minecraft.testLatencyMs?.let { "${it} ms" } ?: "—")
                        if (minecraft.testDetail != null) {
                            Text(minecraft.testDetail, color = AvaTextMuted)
                        }
                    }
                }
            }
            item {
                OutlinedButton(onClick = onRefresh, modifier = Modifier.fillMaxWidth()) {
                    Text("Refresh Minecraft")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
