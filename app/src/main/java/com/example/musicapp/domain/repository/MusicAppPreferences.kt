package com.example.musicapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface MusicAppPreferences {

    suspend fun saveTokenState(token: String)

    fun readTokenState(): Flow<String>

    suspend fun saveUserNameState(userName: String)

    fun readUserNameState(): Flow<String>
}