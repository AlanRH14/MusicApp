package com.example.musicapp.presentation.onboarding.mvi

sealed class OnboardingEffect {
    data class ShowErrorMessage(val message: String) : OnboardingEffect()
    data object NavigationToLogin : OnboardingEffect()
    data object NavigateToHome : OnboardingEffect()
}