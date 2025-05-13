package com.smim.infoze.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    onPrimary = TextWhite,
    secondary = GreenLight,
    onSecondary = TextDark,
    background = Color.White,
    onBackground = TextDark,
    surface = FormBackground,
    onSurface = TextDark,
    error = ErrorRed,
    onError = TextWhite
)

private val DarkColorScheme = darkColorScheme(
    primary = GreenPrimary,
    onPrimary = TextWhite,
    secondary = GreenLight,
    onSecondary = TextDark,
    background = GreenDark,
    onBackground = TextWhite,
    surface = GreenDark,
    onSurface = TextWhite,
    error = ErrorRed,
    onError = TextWhite
)

@Composable
fun InfozeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
