package com.smim.infoze

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smim.infoze.model.Creator
import com.smim.infoze.model.Material
import com.smim.infoze.ui.component.BottomNavigationBar
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(articleId: String, navController: NavController) {
    val context = LocalContext.current
    val allMaterials = Material.sampleMaterials()
    val allCreators = Creator.sampleCreators()

    val material = allMaterials.find { it.id == articleId } ?: return
    val creator = allCreators.find { it.id == material.creatorId }

    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Artykuł") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
        ) {
            Text(
                text = material.title,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = material.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))

            creator?.let {
                Text(
                    text = "Autor: ${it.name}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Text(
                text = "Data publikacji: ${material.publishDate.format(dateFormatter)}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Treść artykułu
            material.content.split("\n\n").forEach { paragraph ->
                Text(
                    text = paragraph,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 12.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            // Placeholdery
            Text(
                text = "Oceń ten artykuł:",
                style = MaterialTheme.typography.titleMedium
            )

            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Star",
                        tint = Color.Gray,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Komentarze:",
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "(Sekcja komentarzy — wkrótce dostępna)",
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}
