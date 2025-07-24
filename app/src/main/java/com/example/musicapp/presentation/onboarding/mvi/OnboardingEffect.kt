package com.example.musicapp.presentation.onboarding.mvi

sealed interface OnboardingEffect {
    data class ShowErrorMessage(val message: String) : OnboardingEffect
    data object NavigateToLogin : OnboardingEffect
    data object NavigateToHome : OnboardingEffect
}