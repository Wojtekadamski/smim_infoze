package com.smim.infoze.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smim.infoze.data.UserPreferences
import com.smim.infoze.ui.component.BottomNavigationBar

@Composable
fun ProfileScreen(navController: NavController) {
    val context = LocalContext.current
    val prefs = remember { UserPreferences(context) }
    val user = prefs.getSavedUserData()
    val username = user?.username ?: prefs.getTemporaryUsername() ?: "Brak danych"
    val email = user?.email ?: "Brak danych"

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Profil użytkownika", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(24.dp))
            Text("Nazwa użytkownika: $username", fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Email: $email", fontSize = 18.sp)
        }
    }
}
