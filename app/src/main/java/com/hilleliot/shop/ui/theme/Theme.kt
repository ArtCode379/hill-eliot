package com.hilleliot.shop.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val LightColorScheme = lightColorScheme(
    primary = HEPrimaryBlue,
    onPrimary = HESurface,
    primaryContainer = HESoftBlue,
    onPrimaryContainer = HEDarkBlue,
    secondary = HEDarkBlue,
    onSecondary = HESurface,
    secondaryContainer = HELightBlue,
    onSecondaryContainer = HETextPrimary,
    background = HEBackground,
    onBackground = HETextPrimary,
    surface = HESurface,
    onSurface = HETextPrimary,
    surfaceVariant = HESoftBlue,
    onSurfaceVariant = HETextSecondary,
    outline = HEBorderMedium,
    outlineVariant = HEBorderLight,
)

private val DarkColorScheme = darkColorScheme(
    primary = HEPrimaryBlue,
    onPrimary = HESurface,
    primaryContainer = HEDarkBlue,
    onPrimaryContainer = HESoftBlue,
    secondary = HESoftBlue,
    onSecondary = HEDarkBlue,
    background = HEDarkBackground,
    onBackground = HESurface,
    surface = HEDarkSurface,
    onSurface = HESurface,
    surfaceVariant = HEDarkCard,
    onSurfaceVariant = HETextLight,
    outline = HEBorderMedium,
)

@Composable
fun HLELTTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
