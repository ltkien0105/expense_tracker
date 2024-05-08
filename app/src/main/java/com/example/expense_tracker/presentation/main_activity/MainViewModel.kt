package com.example.expense_tracker.presentation.main_activity

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    val themeStyle = userPreferencesRepository.themeStyle.map { it ?: ThemeType.SYSTEM.toString() }
    val language = userPreferencesRepository.language.map { it ?: Language.en.name }

    init {
        viewModelScope.launch {
            delay(2000)
            _isLoading.value = false
        }
    }

    fun changeTheme(theme: ThemeType) {
        viewModelScope.launch {
            userPreferencesRepository.saveThemeStylePreference(theme)
        }
    }

    fun changeLanguage(context: Context, language: Language) {
        viewModelScope.launch {
            userPreferencesRepository.saveLanguagePreference(language)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales =
                LocaleList.forLanguageTags(language.name)
        } else {
            AppCompatDelegate.setApplicationLocales(
                LocaleListCompat.forLanguageTags(language.name)
            )
        }
    }
}

enum class ThemeType {
    SYSTEM,
    LIGHT,
    DARK
}

enum class Language {
    en,
    vi,
}