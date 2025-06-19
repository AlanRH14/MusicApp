package com.example.musicapp.presentation.onboarding

sealed interface OnboardingUIEvent {
    data object OnGetStartedClicked : OnboardingUIEvent
}