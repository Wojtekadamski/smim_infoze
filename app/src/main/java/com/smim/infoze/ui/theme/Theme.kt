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
    onSurface = TextDark,
    error = ErrorRed,
    onError = TextWhite,
)

@Composable
fun InfozeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    highContrast: Boolean = false,
    fontScale: Float = 1.0f,
    content: @Composable () -> Unit
) {
    val baseColorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val colorScheme = if (highContrast) {
        baseColorScheme.copy(
            background = Color.Black,
            onBackground = Color.White,
            surface = Color.DarkGray,
            onSurface = Color.Black,
            primary = Color.Yellow,
            onPrimary = Color.Black,
        )
    } else baseColorScheme

    val scaledTypography = Typography.copy(
        bodyLarge = Typography.bodyLarge.copy(fontSize = Typography.bodyLarge.fontSize * fontScale),
        bodyMedium = Typography.bodyMedium.copy(fontSize = Typography.bodyMedium.fontSize * fontScale),
        bodySmall = Typography.bodySmall.copy(fontSize = Typography.bodySmall.fontSize * fontScale)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = scaledTypography,
        content = content
    )
}
