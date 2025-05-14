package com.smim.infoze.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smim.infoze.ui.theme.GreenDark

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        containerColor = GreenDark,
        tonalElevation = 4.dp
    ) {

        val itemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = Color.White,
            unselectedIconColor = Color.White,
            selectedTextColor = Color.White,
            unselectedTextColor = Color.White,
            indicatorColor = GreenDark // brak zmiany tła przy aktywnym
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.popBackStack() },
            icon = { Icon(Icons.Filled.ArrowBack, contentDescription = "Wstecz") },
            label = { Text("Wstecz") },
            colors = itemColors
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("home") },
            icon = { Icon(Icons.Filled.Home, contentDescription = "Strona główna") },
            label = { Text("Home") },
            colors = itemColors
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("profile") },
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profil") },
            label = { Text("Profil") },
            colors = itemColors
        )
    }
}
