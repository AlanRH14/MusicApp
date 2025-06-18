package com.example.musicapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface MusicAppPreferences {

    suspend fun saveTokenState(token: String)

    fun readOnboardingState(): Flow<Boolean>
}