package com.example.myapplication.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    secondary = DarkSecondary,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    onSurfaceVariant = DarkOnSurfaceVariant
)

private val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    secondary = LightSecondary,
    surface = LightSurface,
    onSurface = LightOnSurface,
    onSurfaceVariant = LightOnSurfaceVariant
)

val LocalBackgroundGradient = staticCompositionLocalOf<Brush> {
    Brush.verticalGradient(
        listOf(Color.White, Color.White)
    )
}

@Composable
fun ProfileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors =
        if (darkTheme) DarkColorScheme
        else LightColorScheme

    val gradient =
        if (darkTheme) {

            Brush.verticalGradient(
                colors = listOf(
                    Color(0xFF5A4450),
                    Color(0xFF382B34),
                    Color(0xFF241D22)
                )
            )

        } else {

            Brush.verticalGradient(
                colors = listOf(
                    Cream,
                    Peach,
                    CherryBlossom,
                    Lavender,
                    Mint
                )
            )

        }

    CompositionLocalProvider(
        LocalBackgroundGradient provides gradient
    ) {

        MaterialTheme(
            colorScheme = colors,
            typography = Typography,
            content = content
        )

    }

}