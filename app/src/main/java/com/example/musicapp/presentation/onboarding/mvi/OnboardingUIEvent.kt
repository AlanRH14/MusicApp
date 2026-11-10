package com.example.musicapp.presentation.onboarding.mvi

sealed interface OnboardingUIEvent {
    data object OnGetStartedClicked : OnboardingUIEvent
}
