package com.smim.infoze

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smim.infoze.data.UserPreferences
import com.smim.infoze.model.Creator
import com.smim.infoze.ui.component.BottomNavigationBar
import com.smim.infoze.ui.component.ResourceButton
import com.smim.infoze.ui.component.CreatorAvatar
import kotlinx.coroutines.delay
import com.smim.infoze.ui.component.CreatorPickerDialog
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navController: NavController) {
    val allCreators = remember { Creator.sampleCreators() }
    val favoriteCreators = remember { mutableStateListOf<Creator>() }
    val context = LocalContext.current
    val prefs = remember { UserPreferences(context) }
    val savedUser = prefs.getSavedUserData()
    val username = savedUser?.username ?: prefs.getTemporaryUsername() ?: "użytkowniku"
    val facts = listOf(
        "Jedna turbina wiatrowa o mocy 3 MW może zasilić 1500 domów.",
        "Panele słoneczne są w stanie pracować nawet w pochmurne dni.",
        "Energia wodna to najstarsza forma energii odnawialnej.",
        "Biomasa może być źródłem paliwa do ogrzewania domów."
    )
    var currentFact by remember { mutableStateOf(facts.random()) }
    var showDialog by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        val favorites = prefs.getFavoriteCreators(savedUser?.email ?: "")
        favoriteCreators.clear()
        favoriteCreators.addAll(allCreators.filter { it.id in favorites })
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(6000)
            currentFact = facts.random()
        }
    }

    fun saveFavorites() {
        val ids = favoriteCreators.map { it.id }.toSet()
        prefs.saveFavoriteCreators(savedUser?.email ?: "", ids)
    }

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Witaj, $username!",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(Modifier.height(16.dp))

            Text("Ulubieni twórcy", style = MaterialTheme.typography.titleMedium)
            Row(
                modifier = Modifier
                    .horizontalScroll(rememberScrollState())
                    .padding(vertical = 8.dp)
            ) {
                favoriteCreators.forEach { creator ->
                    CreatorAvatar(creator = creator, isFavorite = true) {
                        navController.navigate("creator_profile/${creator.id}")
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(onClick = { showDialog = true }) {
                    Text("Dodaj twórcę")
                }
            }

            if (showDialog) {
                CreatorPickerDialog(
                    allCreators = allCreators,
                    favoriteCreators = favoriteCreators,
                    onDismiss = { showDialog = false },
                    onToggleFavorite = { creator ->
                        if (favoriteCreators.contains(creator)) {
                            favoriteCreators.remove(creator)
                        } else {
                            favoriteCreators.add(creator)
                        }
                        saveFavorites()
                    }
                )
            }

            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9))
            ) {
                Text(
                    currentFact,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(16.dp))

            Text("Nasze zasoby", style = MaterialTheme.typography.titleMedium)

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ResourceButton(
                    title = "Artykuły",
                    icon = Icons.Default.Info,
                    onClick = { navController.navigate("articles") }
                )
                ResourceButton("Podcasty", Icons.Default.Phone) { navController.navigate("podcast") }
                ResourceButton("Raporty", Icons.Default.ShoppingCart) { navController.navigate("report") }
            }

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    prefs.clearLoginDataOnly()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Wyloguj się", textAlign = TextAlign.Center)
            }
        }
    }
}
