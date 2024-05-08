package com.example.expense_tracker.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expense_tracker.R
import com.example.expense_tracker.presentation.main_activity.Language
import com.example.expense_tracker.presentation.main_activity.MainViewModel
import com.example.expense_tracker.presentation.main_activity.ThemeType
import com.example.expense_tracker.presentation.settings.component.SettingsItem
import com.example.expense_tracker.ui.theme.ReplacementTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var isChangeThemeDialogShowed by remember {
        mutableStateOf(false)
    }
    var isChangeLanguageDialogShowed by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .background(color = ReplacementTheme.colorScheme.background)
            .fillMaxHeight()
            .fillMaxWidth()
    ) {
        SettingsItem(
            icon = R.drawable.ic_import,
            title = stringResource(R.string._import),
            contentDescription = stringResource(R.string._import)
        )
        SettingsItem(
            icon = R.drawable.ic_backup,
            title = stringResource(R.string.backup),
            contentDescription = stringResource(R.string.backup)
        )
        SettingsItem(
            icon = R.drawable.ic_theme,
            title = stringResource(R.string.theme),
            contentDescription = stringResource(R.string.theme),
            modifier = Modifier.clickable { isChangeThemeDialogShowed = true },
        )
        SettingsItem(
            icon = R.drawable.ic_languages,
            title = stringResource(R.string.languages),
            contentDescription = stringResource(R.string.languages),
            modifier = Modifier.clickable { isChangeLanguageDialogShowed = true },
        )
        SettingsItem(
            icon = R.drawable.ic_about,
            title = stringResource(R.string.about),
            contentDescription = stringResource(R.string.about)
        )
    }
    if (isChangeThemeDialogShowed) {
        BasicAlertDialog(
            onDismissRequest = { isChangeThemeDialogShowed = false }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = ReplacementTheme.colorScheme.background
                    )
            ) {
                TextButton(
                    onClick = {
                        mainViewModel.changeTheme(ThemeType.SYSTEM)
                        isChangeThemeDialogShowed = false
                    }
                ) {
                    Text(
                        stringResource(R.string.system_default),
                        color = ReplacementTheme.colorScheme.onBackground
                    )
                }
                TextButton(
                    onClick = {
                        mainViewModel.changeTheme(ThemeType.LIGHT)
                        isChangeThemeDialogShowed = false
                    }
                ) {
                    Text(
                        stringResource(R.string.light_mode),
                        color = ReplacementTheme.colorScheme.onBackground
                    )
                }
                TextButton(
                    onClick = {
                        mainViewModel.changeTheme(ThemeType.DARK)
                        isChangeThemeDialogShowed = false
                    }
                ) {
                    Text(
                        stringResource(R.string.dark_mode),
                        color = ReplacementTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }

    if (isChangeLanguageDialogShowed) {
        BasicAlertDialog(
            onDismissRequest = { isChangeLanguageDialogShowed = false }
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        color = ReplacementTheme.colorScheme.background
                    )
            ) {
                TextButton(
                    onClick = {
                        mainViewModel.changeLanguage(context, Language.en)
                        isChangeLanguageDialogShowed = false
                    }
                ) {
                    Text(
                        "English",
                        color = ReplacementTheme.colorScheme.onBackground
                    )
                }
                TextButton(
                    onClick = {
                        mainViewModel.changeLanguage(context, Language.vi)
                        isChangeLanguageDialogShowed = false
                    }
                ) {
                    Text(
                        "Vietnamese",
                        color = ReplacementTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}


