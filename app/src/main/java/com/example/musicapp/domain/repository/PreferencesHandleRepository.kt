package com.example.musicapp.domain.repository

import com.example.musicapp.common.PreferencesKey
import kotlinx.coroutines.flow.Flow

interface PreferencesHandleRepository<T> {

    suspend fun saveState(key: PreferencesKey<T>, value: T)

    fun readState(key: PreferencesKey<T>): Flow<T>
}