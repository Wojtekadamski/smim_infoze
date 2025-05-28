package com.smim.infoze.ui.screen.settings

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.smim.infoze.data.UserPreferences

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AccessibilitySettingsScreen(
    navController: NavController,
    userEmail: String,
    userPrefs: UserPreferences,
    onSettingsSaved: () -> Unit
) {
    var darkMode by remember { mutableStateOf(userPrefs.getDarkMode(userEmail)) }
    var highContrast by remember { mutableStateOf(userPrefs.getHighContrast(userEmail)) }
    var fontScale by remember { mutableStateOf(userPrefs.getFontScale(userEmail)) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Ustawienia dostępności", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(24.dp))

        SwitchSetting("Tryb ciemny", darkMode) { darkMode = it }

        SwitchSetting("Wysoki kontrast", highContrast) { highContrast = it }

        SliderSetting(
            label = "Rozmiar czcionki",
            value = fontScale,
            onValueChange = { fontScale = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            userPrefs.saveAccessibilitySettings(userEmail, darkMode, highContrast, fontScale)
            onSettingsSaved()
            navController.popBackStack()
        }) {
            Text("Zapisz")
        }
    }
}

@Composable
fun SwitchSetting(label: String, value: Boolean, onValueChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label)
        Switch(checked = value, onCheckedChange = onValueChange)
    }
}

@Composable
fun SliderSetting(label: String, value: Float, onValueChange: (Float) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("$label: ${String.format("%.1f", value)}x")
        Slider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0.8f..1.8f,
            steps = 5
        )
    }
}
