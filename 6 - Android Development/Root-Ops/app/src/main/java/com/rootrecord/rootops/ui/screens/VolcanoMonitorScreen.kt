package com.rootrecord.rootops.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rootrecord.rootops.ops.KilaueaInfo
import com.rootrecord.rootops.ops.formatIso
import com.rootrecord.rootops.ui.theme.AvaAccent
import com.rootrecord.rootops.ui.theme.AvaTextMuted
import com.rootrecord.rootops.ui.theme.AvaWarning
import com.rootrecord.rootops.ui.theme.DetailScaffold
import com.rootrecord.rootops.ui.theme.InfoRow
import com.rootrecord.rootops.ui.theme.PanelCard
import com.rootrecord.rootops.ui.theme.StatusPill
import com.rootrecord.rootops.ui.theme.WaitingForLive

@Composable
fun VolcanoMonitorScreen(kilauea: KilaueaInfo?, onBack: () -> Unit, onRefresh: () -> Unit = {}) {
    val level = kilauea?.alertLevel?.replaceFirstChar { it.uppercase() } ?: "Unknown"
    DetailScaffold(title = "Kīlauea Monitor", subtitle = "USGS Hawaiian Volcano Observatory", onBack = onBack) { insets ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(insets).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                StatusPill(
                    if (kilauea?.ok == true) level else "No live alert",
                    if (kilauea?.ok == true) AvaAccent else AvaWarning,
                )
            }
            if (kilauea == null) {
                item { WaitingForLive("Kīlauea is not in the current board snapshot.") }
            } else {
                item {
                    PanelCard {
                        Text(level, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleMedium, color = AvaAccent)
                        Text(kilauea.detail ?: "Alert level from the HVO Kīlauea feed.", color = AvaTextMuted)
                        InfoRow("Economy multiplier", kilauea.multiplier?.toString() ?: "—")
                        InfoRow("Nearby events", kilauea.eventsNearby?.toString() ?: "—")
                        InfoRow("Max magnitude", kilauea.maxMagnitude?.toString() ?: "—")
                        InfoRow("Updated", formatIso(kilauea.updatedAt))
                        InfoRow("Source", kilauea.source ?: "—")
                    }
                }
            }
            item {
                OutlinedButton(onClick = onRefresh, modifier = Modifier.fillMaxWidth()) {
                    Text("Refresh Kīlauea")
                }
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}
