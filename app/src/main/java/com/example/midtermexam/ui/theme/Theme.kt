package com.example.midtermexam.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = LightMaroon,
    secondary = Rose,
    tertiary = SoftRose,

    background = DarkMaroon,
    surface = DarkSurface,

    onPrimary = White,
    onSecondary = White,
    onTertiary = DarkText,
    onBackground = White,
    onSurface = White
)

private val LightColorScheme = lightColorScheme(
    primary = Maroon,
    secondary = Rose,
    tertiary = LightMaroon,

    background = Cream,
    surface = SoftCream,

    onPrimary = White,
    onSecondary = DarkText,
    onTertiary = White,
    onBackground = DarkText,
    onSurface = DarkText
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}