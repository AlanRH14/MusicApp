package com.example.musicapp.presentation.onboarding

sealed interface OnboardingUIEvent {
    data object CheckAuthStatus : OnboardingUIEvent
    data object OnGetStartedClicked : OnboardingUIEvent
}