package com.smim.infoze

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun InfoZEApp() {
    val navController = rememberNavController()
    InfoZENavGraph(navController = navController)
}

@Composable
fun InfoZENavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "start") {
        composable("start") { StartScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("news") { /* TODO: News screen */ }
        composable("podcast") { /* TODO: Podcast screen */ }
        composable("report") { /* TODO: Report screen */ }
    }
}
