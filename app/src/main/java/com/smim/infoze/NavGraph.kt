package com.smim.infoze

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smim.infoze.ui.screen.ArticleDetailScreen
import com.smim.infoze.ui.screen.ArticlesScreen
import com.smim.infoze.ui.screen.CreatorProfileScreen
import com.smim.infoze.ui.screen.EditProfileScreen
import com.smim.infoze.ui.screen.HomeScreen
import com.smim.infoze.ui.screen.LoginScreen
import com.smim.infoze.ui.screen.PodcastScreen
import com.smim.infoze.ui.screen.RegisterScreen
import com.smim.infoze.ui.screen.StartScreen
import com.smim.infoze.ui.screen.UserProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoZEApp() {
    val navController = rememberNavController()
    InfoZENavGraph(navController = navController)
}

@RequiresApi(Build.VERSION_CODES.O)
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
        composable("creator_profile/{creatorId}") { backStackEntry ->
            val creatorId = backStackEntry.arguments?.getString("creatorId") ?: ""
            CreatorProfileScreen(creatorId = creatorId, navController = navController)
        }
        composable("articles") {
            ArticlesScreen(navController)
        }
        composable("profile") { UserProfileScreen(navController) }
        composable("edit_profile") {
            EditProfileScreen(navController = navController)
        }
        composable("articleDetail/{articleId}") { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId") ?: ""
            ArticleDetailScreen(articleId = articleId, navController = navController)
        }
        composable("podcast") {
            PodcastScreen(navController)
        }
    }
}
