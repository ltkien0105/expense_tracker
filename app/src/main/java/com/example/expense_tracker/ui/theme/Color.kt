package com.example.expense_tracker.ui.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

//External color
val WhiteAlpha6 = Color(0x0FFFFFFF)
val WhiteAlpha15 = Color(0x0FFFFFFF)
val LightGray = Color(0xFFDDDDDD)
val Gray = Color(0xFF666666)
val AppBarColor = Color(0xFF429690)
val LightWhiteGreen = Color(0xFFF0F6F5)
val LightGreen = Color(0xFF25A969)
val Red = Color(0xFFF95B51)
val DarkGreen = Color(0xFF438883)
//End external color

data class ReplacementColorScheme(
    val primary: Color,
    val onPrimary: Color,
    val background: Color,
    val onBackground: Color,
    val bottomNavigationBackground: Color,
    val onBottomNavigationBackground: Color,
    val onBottomNavigationBackgroundFilled: Color,
    val incomeLabel: Color,
    val expenseLabel: Color,
    val appBar: Color = AppBarColor,
    val onAppBar: Color = Color.White,
    val indicatorTab: Color,
    val lineBudget: Color,
)

val LocalReplacementColor = staticCompositionLocalOf {
    ReplacementColorScheme(
        primary = Color.Unspecified,
        onPrimary = Color.Unspecified,
        background = Color.Unspecified,
        onBackground = Color.Unspecified,
        bottomNavigationBackground = Color.Unspecified,
        onBottomNavigationBackground = Color.Unspecified,
        onBottomNavigationBackgroundFilled = Color.Unspecified,
        incomeLabel = Color.Unspecified,
        expenseLabel = Color.Unspecified,
        indicatorTab = Color.Unspecified,
        lineBudget = Color.Unspecified
    )
}