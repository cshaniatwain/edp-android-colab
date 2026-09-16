package edu.liceo.fieldkit.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppBackground = Color(0xFFF9F6FF)
private val CardBackground = Color(0xFFFCFAFF)
private val PrimaryBlue = Color(0xFF5369A0)
private val DarkText = Color(0xFF252638)
private val SecondaryText = Color(0xFF6F7080)
private val CircleColor = Color(0xFFB7C7F5)

@Composable
fun LiceoFieldKitTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = PrimaryBlue,
            background = AppBackground,
            surface = CardBackground
        ),
        content = content
    )
}
