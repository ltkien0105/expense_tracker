package com.example.expense_tracker.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.expense_tracker.R
import com.example.expense_tracker.presentation.main_activity.MainViewModel
import com.example.expense_tracker.presentation.settings.component.SettingsItem
import kotlinx.coroutines.flow.collect

@Composable
fun SettingsScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val isDarkMode by mainViewModel.isDarkMode.collectAsState(initial = false)

    LaunchedEffect(key1 = isDarkMode) {
        println("Dark Theme2: $isDarkMode")
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(color = Color.White)
    ) {
        SettingsItem(
            icon = R.drawable.ic_import,
            title = "Import",
            contentDescription = "Import"
        )
        SettingsItem(
            icon = R.drawable.ic_backup,
            title = "Backup",
            contentDescription = "Backup"
        )
        SettingsItem(
            icon = R.drawable.ic_theme,
            title = "Dark Mode",
            contentDescription = "Theme",
            isChecked = isDarkMode,
            onCheckedChange = { mainViewModel.toggleTheme(it) }
        )
        SettingsItem(
            icon = R.drawable.ic_languages,
            title = "Languages",
            contentDescription = "Languages"
        )
        SettingsItem(
            icon = R.drawable.ic_about,
            title = "About",
            contentDescription = "About"
        )
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}


