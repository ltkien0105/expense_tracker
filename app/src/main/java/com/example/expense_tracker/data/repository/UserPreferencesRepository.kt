package com.example.expense_tracker.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.expense_tracker.presentation.main_activity.Language
import com.example.expense_tracker.presentation.main_activity.ThemeType
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesRepository @Inject constructor(
    @ApplicationContext context: Context,
) {
    private val dataStore: DataStore<Preferences> = context.dataStore

    private companion object {
        //        val THEME_STYLE = booleanPreferencesKey("is_dark_mode")
        const val TAG = "UserPreferencesRepo"
        val THEME_STYLE = stringPreferencesKey("theme_style")
        val LANGUAGE = stringPreferencesKey("language")
    }

    val themeStyle: Flow<String?> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[THEME_STYLE]
        }

    val language: Flow<String?> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading preferences.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[LANGUAGE]
        }

    suspend fun saveThemeStylePreference(theme: ThemeType) {
        dataStore.edit { preferences ->
            preferences[THEME_STYLE] = theme.name
        }
    }

    suspend fun saveLanguagePreference(language: Language) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE] = language.name
        }
    }
}