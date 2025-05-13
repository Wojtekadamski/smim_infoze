package com.smim.infoze

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smim.infoze.data.AppDatabase
import com.smim.infoze.data.UserPreferences
import com.smim.infoze.model.Creator
import com.smim.infoze.model.Material
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatorProfileScreen(creatorId: String, navController: NavController) {
    val context = LocalContext.current
    val userPrefs = remember { UserPreferences(context) }
    val allCreators = remember { Creator.sampleCreators() }
    val allMaterials = remember { Material.sampleMaterials() }
    val creator = allCreators.find { it.id == creatorId } ?: return
    val materials = allMaterials.filter { it.creatorId == creatorId }

    var followersCount by remember { mutableStateOf(0) }

    // Ładowanie z bazy użytkowników i liczenie followerów
    LaunchedEffect(Unit) {
        val userDao = AppDatabase.getDatabase(context).userDao()
        val users = withContext(Dispatchers.IO) { userDao.getAllUsers() }
        followersCount = users.count {
            val prefs = UserPreferences(context)
            val favs = prefs.getFavoriteCreators(it.email)
            favs.contains(creatorId)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(creator.name) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Profil
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = creator.imageRes),
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = creator.name,
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
                ) {
                    Text(
                        text = "${materials.size}\nMateriałów",
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "$followersCount\nObserwujących",
                        textAlign = TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Artykuły",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(start = 16.dp)
            )

            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(materials) { material ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Image(
                                painter = painterResource(id = material.thumbnailRes),
                                contentDescription = null,
                                modifier = Modifier.size(64.dp)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(material.title, fontWeight = FontWeight.Bold)
                                Text(material.description, maxLines = 2)
                            }
                        }
                    }
                }
            }
        }
    }
}
