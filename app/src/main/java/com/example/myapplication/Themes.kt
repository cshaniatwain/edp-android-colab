package com.example.greetingapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.
Composable

private val GreetingColorScheme = lightColorScheme(
    primary = PrimaryMM,
    onPrimary = OnPrimaryMM,

    secondary = SecondaryMM,
    onSecondary = Brown,

    background = BackgroundMM,
    onBackground = OnBackgroundMM,

    surface = SurfaceMM,
    onSurface = OnSurfaceMM,

    primaryContainer = SoftCream,
    onPrimaryContainer = Brown,

    secondaryContainer = Peach,
    onSecondaryContainer = 
Brown
)

@Composable
fun GreetingAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GreetingColorScheme,
        typography = Typography,
        content = content
    )
}
