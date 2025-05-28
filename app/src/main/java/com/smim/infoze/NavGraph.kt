package com.smim.infoze

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smim.infoze.data.UserPreferences
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
import com.smim.infoze.ui.screen.settings.AccessibilitySettingsScreen
import com.smim.infoze.ui.theme.InfozeTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoZEApp() {
    val context = LocalContext.current
    val userPreferences = remember { UserPreferences(context) }
    val currentUserEmail = userPreferences.getLoggedInUser()

    // Domyślne wartości jeśli użytkownik nie jest zalogowany
    val darkTheme = remember { mutableStateOf(false) }
    val highContrast = remember { mutableStateOf(false) }
    val fontScale = remember { mutableStateOf(1f) }

    val settingsReloadTrigger = remember { mutableStateOf(0) }


    // Wczytaj ustawienia użytkownika
    LaunchedEffect(currentUserEmail, settingsReloadTrigger.value) {
        currentUserEmail?.let {
            darkTheme.value = userPreferences.getDarkMode(it)
            highContrast.value = userPreferences.getHighContrast(it)
            fontScale.value = userPreferences.getFontScale(it)
        }
    }

    InfozeTheme(
        darkTheme = darkTheme.value,
        highContrast = highContrast.value,
        fontScale = fontScale.value
    ) {
        val navController = rememberNavController()
        InfoZENavGraph(
            navController = navController,
            userEmail = currentUserEmail,
            userPrefs = userPreferences,
            onAccessibilitySettingsChanged = {
                settingsReloadTrigger.value++}
        )
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun InfoZENavGraph(navController: NavHostController, userEmail: String?,
                   userPrefs: UserPreferences, onAccessibilitySettingsChanged: () -> Unit) {
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
        composable("accessibility_settings") {
            AccessibilitySettingsScreen(
                navController = navController,
                userEmail = userEmail ?: "",
                userPrefs = userPrefs,
                onSettingsSaved = {
                    onAccessibilitySettingsChanged()
                }
            )
        }
    }
}
