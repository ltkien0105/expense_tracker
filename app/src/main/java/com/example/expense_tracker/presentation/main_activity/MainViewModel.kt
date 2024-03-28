package com.example.expense_tracker.presentation.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expense_tracker.data.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
): ViewModel() {
    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    val themeStyle = userPreferencesRepository.themeStyle.map { it ?: ThemeType.SYSTEM.toString() }

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
}

enum class ThemeType {
    SYSTEM,
    LIGHT,
    DARK
}