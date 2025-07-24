package com.example.musicapp.presentation.onboarding.mvi

sealed interface OnboardingEffect {
    data object NavigateToLogin : OnboardingEffect
    data object NavigateToHome : OnboardingEffect
}