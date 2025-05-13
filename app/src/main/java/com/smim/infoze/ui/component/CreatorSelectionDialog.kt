package com.smim.infoze.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smim.infoze.model.Creator

@Composable
fun CreatorSelectionDialog(
    allCreators: List<Creator>,
    selectedCreators: List<Creator>,
    onDismiss: () -> Unit,
    onToggleFavorite: (Creator) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = { Text("Wybierz ulubionych twórców") },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                allCreators.forEach { creator ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onToggleFavorite(creator) }
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = creator.name,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = if (creator in selectedCreators) Color(0xFFFFD700) else Color.Gray
                        )
                    }
                }
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Zamknij")
            }
        }
    )
}
