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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rootrecord.rootops.ops.WeatherInfo
import com.rootrecord.rootops.ops.formatIso
import com.rootrecord.rootops.ui.theme.AvaAccent
import com.rootrecord.rootops.ui.theme.AvaPanel
import com.rootrecord.rootops.ui.theme.AvaTextMuted
import com.rootrecord.rootops.ui.theme.AvaWarning
import com.rootrecord.rootops.ui.theme.DetailScaffold
import com.rootrecord.rootops.ui.theme.PanelCard
import com.rootrecord.rootops.ui.theme.StatusPill
import com.rootrecord.rootops.ui.theme.WaitingForLive

@Composable
fun WeatherScreen(weather: WeatherInfo?, onBack: () -> Unit, onRefresh: () -> Unit = {}) {
    DetailScaffold(title = "Weather & NWS Hawaii", onBack = onBack) { insets ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(insets).padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            item {
                StatusPill(
                    if (weather?.ok == true) "Live" else "No live weather",
                    if (weather?.ok == true) AvaAccent else AvaWarning,
                )
            }
            if (weather == null) {
                item { WaitingForLive("NWS Hawaii is not in the current board snapshot.") }
            } else {
                item {
                    PanelCard {
                        Text(weather.period ?: "Current period", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                        Text(
                            weather.temperatureF?.let { "$it°F" } ?: "—",
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(weather.forecast ?: weather.detail ?: "No forecast text in this snapshot.", color = AvaTextMuted)
                    }
                }
                item {
                    Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                        PanelStat(
                            label = "Active Alerts",
                            value = weather.alertsActive?.toString() ?: "—",
                            detail = when {
                                weather.alertsActive == null -> "Not in snapshot"
                                weather.alertsActive == 0 -> "All clear"
                                else -> "Alerts active"
                            },
                            modifier = Modifier.weight(1f),
                        )
                        PanelStat(
                            label = "Last pull",
                            value = formatIso(weather.updatedAt),
                            detail = "From the Hawaii state weather report",
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
            item {
                OutlinedButton(onClick = onRefresh, modifier = Modifier.fillMaxWidth()) {
                    Text("Refresh live weather")
                }
            }
            item {
                Text(
                    "National Weather Service Honolulu. The phone shows today's zone line from the Hawaii state report.",
                    style = MaterialTheme.typography.labelSmall,
                    color = AvaTextMuted,
                )
            }
            item { Spacer(Modifier.height(24.dp)) }
        }
    }
}

@Composable
private fun PanelStat(label: String, value: String, detail: String, modifier: Modifier = Modifier) {
    Card(colors = CardDefaults.cardColors(containerColor = AvaPanel), modifier = modifier) {
        Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(label, style = MaterialTheme.typography.labelSmall, color = AvaTextMuted)
            Text(value, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
            Text(detail, style = MaterialTheme.typography.labelSmall, color = AvaAccent)
        }
    }
}
