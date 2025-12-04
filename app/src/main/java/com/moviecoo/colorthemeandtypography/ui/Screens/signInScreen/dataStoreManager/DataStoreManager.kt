package com.moviecoo.colorthemeandtypography.ui.Screens.signInScreen.dataStoreManager

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

object DataStoreManager {
    private val Context.dataStore by preferencesDataStore("app_prefs")
    val HAS_LAUNCHED_ONBOARDING = booleanPreferencesKey("has_launched_onboarding")

    val LAUNCH_COUNT = intPreferencesKey("launch_count")

    suspend fun incrementLaunchCount(context: Context) {
        context.dataStore.edit { prefs ->
            val current = prefs[LAUNCH_COUNT] ?: 0
            prefs[LAUNCH_COUNT] = current + 1
        }
    }

    fun getLaunchCount(context: Context): Flow<Int> {
        return context.dataStore.data.map { prefs ->
            prefs[LAUNCH_COUNT] ?: 0
        }
    }

    suspend fun resetLaunchCount(context: Context) {
        context.dataStore.edit { prefs ->
            prefs[LAUNCH_COUNT] = 0
        }
    }

    suspend fun setHasLaunchedOnboarding(context: Context, value: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[HAS_LAUNCHED_ONBOARDING] = value
        }
    }

    suspend fun getHasLaunchedOnboarding(context: Context): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[HAS_LAUNCHED_ONBOARDING] ?: false
    }

}
