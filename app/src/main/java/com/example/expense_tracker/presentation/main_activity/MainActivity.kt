package com.example.expense_tracker.presentation.main_activity

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.LocaleListCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.expense_tracker.presentation.main.MainScreen
import com.example.expense_tracker.ui.theme.ExpenseTrackerTheme
import com.example.expense_tracker.ui.theme.ReplacementTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainViewModel.isLoading.value
            }
        }

        setContent {
            val themeStyle = mainViewModel.themeStyle.collectAsState(initial = false)

            val isDarkTheme = when (themeStyle.value) {
                ThemeType.SYSTEM.toString() -> isSystemInDarkTheme()
                ThemeType.LIGHT.toString() -> false
                ThemeType.DARK.toString() -> true
                else -> isSystemInDarkTheme()
            }

            ExpenseTrackerTheme(
                darkTheme = isDarkTheme
            ) {
                val context = LocalContext.current

                val language =
                    mainViewModel.language.collectAsState(initial = "en")

                val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(language.value)
                AppCompatDelegate.setApplicationLocales(appLocale)

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = ReplacementTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}