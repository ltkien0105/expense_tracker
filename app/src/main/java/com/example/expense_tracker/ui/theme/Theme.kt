package com.example.expense_tracker.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = ReplacementColorScheme(
    primary = Color(0xFF73bab5),
    onPrimary = Color.White,
    background = Color(0xFF121212),
    onBackground = Color.White
)

private val LightColorScheme = ReplacementColorScheme(
    primary = DarkGreen,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black
)

@Composable
fun ExpenseTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = AppBarColor.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    val replacementTypography = ReplacementTypography()

    CompositionLocalProvider(
        LocalReplacementTypography provides replacementTypography,
        LocalReplacementColor provides colorScheme,
        content = content
    )
}

object ReplacementTheme {
    val typography: ReplacementTypography
        @Composable
        get() = LocalReplacementTypography.current

    val colorScheme: ReplacementColorScheme
        @Composable
        get() = LocalReplacementColor.current
}