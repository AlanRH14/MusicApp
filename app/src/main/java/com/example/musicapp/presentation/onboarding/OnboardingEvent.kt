package com.example.musicapp.presentation.onboarding

sealed class OnboardingEvent {
    data class ShowErrorMessage(val message: String) : OnboardingEvent()
}