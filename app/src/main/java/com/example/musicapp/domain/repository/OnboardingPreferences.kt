package com.example.musicapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface OnboardingPreferences {

    suspend fun saveOnboardingState(completed: Boolean)

    fun readOnboardingState(): Flow<Boolean>
}