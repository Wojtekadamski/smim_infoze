package com.smim.infoze.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.smim.infoze.model.Creator

@Composable
fun CreatorPickerDialog(
    allCreators: List<Creator>,
    favoriteCreators: List<Creator>,
    onDismiss: () -> Unit,
    onToggleFavorite: (Creator) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {},
        title = { Text("Wybierz ulubionych twórców") },
        text = {
            Column {
                allCreators.forEach { creator ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                            .clickable { onToggleFavorite(creator) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = creator.imageRes),
                            contentDescription = null,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 12.dp)
                        )
                        Text(
                            text = creator.name,
                            modifier = Modifier.weight(1f)
                        )
                        Icon(
                            imageVector = if (favoriteCreators.contains(creator)) Icons.Filled.Star else Icons.Outlined.Star,
                            contentDescription = null,
                            tint = if (favoriteCreators.contains(creator)) Color(0xFFFFD700) else Color.Gray
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
