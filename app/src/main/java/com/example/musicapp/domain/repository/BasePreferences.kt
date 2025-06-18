package com.example.musicapp.domain.repository

import com.example.musicapp.common.PreferencesKey
import kotlinx.coroutines.flow.Flow

interface BasePreferences<T> {

    suspend fun saveState(key: PreferencesKey<T>, data: T)

    fun readState(key: PreferencesKey<T>): Flow<T>
}