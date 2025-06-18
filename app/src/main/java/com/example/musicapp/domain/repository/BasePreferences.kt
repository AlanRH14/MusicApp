package com.example.musicapp.domain.repository

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface BasePreferences<T> {

    suspend fun saveState(key: Preferences.Key<T>, data: T)

    fun readState(key: Preferences.Key<T>): Flow<T>
}