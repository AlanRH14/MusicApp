package com.example.musicapp.presentation.login

sealed interface LoginUIEvent {
    data class OnEmailChange(val email: String) : LoginUIEvent
    data class OnPasswordChange(val password: String) : LoginUIEvent
    data object OnTogglePasswordVisibility : LoginUIEvent
    data object OnLoginClicked : LoginUIEvent
    data object OnRememberMeActive : LoginUIEvent
    data object OnRegisterClicked : LoginUIEvent
    data object OnForgotPasswordClicked : LoginUIEvent
    data object OnBackClicked : LoginUIEvent
    data object OnDismissed : LoginUIEvent
}