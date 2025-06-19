package com.example.musicapp.domain.repository

import com.example.musicapp.common.PreferencesKey
import kotlinx.coroutines.flow.Flow

interface DataStoreHandleRepository {

    suspend fun <T> saveState(key: PreferencesKey<T>, value: T)

    fun <T> readState(key: PreferencesKey<T>): Flow<T>
}