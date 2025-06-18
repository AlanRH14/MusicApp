package com.example.musicapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.musicapp.domain.repository.MusicAppPreferences
import com.example.musicapp.utils.Constants.PREFERENCES_NAME
import com.example.musicapp.utils.Constants.TOKEN_PREFERENCES_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class MusicAppPreferencesImpl(private val mContext: Context) : MusicAppPreferences {

    private val dataStore = mContext.dataStore

    private object PreferencesKey {
        val tokenKey = stringPreferencesKey(name = TOKEN_PREFERENCES_KEY)
    }

    override suspend fun saveTokenState(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.tokenKey] = token
        }
    }

    override fun readOnboardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                val onboardingState = preferences[PreferencesKey.tokenKey] ?: false
                onboardingState
            }
    }
}