package com.example.musicapp.presentation.onboarding.mvi

data class OnboardingState(
    val isLoading: Boolean = false,
    val isUserLoggedIn: Boolean = false
)
