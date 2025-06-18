package com.example.musicapp.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.musicapp.common.PreferencesKey
import com.example.musicapp.domain.repository.PreferencesHandleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

class PreferencesHandleRepositoryHandleImpl<T>(
    private val dataStore: DataStore<Preferences>
) : PreferencesHandleRepository<T> {

    override suspend fun saveState(key: PreferencesKey<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key.preferencesKey] = value
        }
    }

    override fun readState(key: PreferencesKey<T>): Flow<T> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[key.preferencesKey] ?: key.defaultValue
            }
    }
}