package com.example.weatherapp.presentation.design

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.ln


@Immutable
data class Colors(
    val primary: Color,
    val secondary: Color,
    val contentPrimary: Color,
) {


    companion object {
        val LightColors = Colors(
            primary = Color(0xFF87CEFA),
            secondary = Color(0xFF060414),
            contentPrimary = Color(0xFFFFFFFF),
        )

        val DarkColors = Colors(
            primary = Color(0xB2060414),
            secondary = Color(0xFFFFFFFF),
            contentPrimary = Color(0xB2060414),
        )
    }

    @Composable
    fun toColorScheme() = MaterialTheme.colorScheme.copy(
        primary = primary,
        onPrimaryContainer = contentPrimary,
        secondary = secondary,
        primaryContainer = Color(0xFF6ff7f6),
        onSecondary = Color(0xFFFFFFFF),
        secondaryContainer = Color(0xFFcce8e7),
        tertiary = Color(0xFF4b607c),
        onTertiary = Color(0xFFFFFFFF),
        tertiaryContainer = Color(0xFFd3e4ff),
        onBackground = Color(0xFF191c1c),
        onSurface = Color(0xFF191C1C),
        surfaceVariant = Color(0xFFDAE5E4),
        onSurfaceVariant = Color(0xFF3F4948),
        outline = Color(0xFF6f7979),
    )
}
