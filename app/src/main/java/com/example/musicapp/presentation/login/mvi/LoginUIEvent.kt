package com.example.musicapp.presentation.login.mvi

sealed interface LoginUIEvent {
    data class OnEmailUpdate(val email: String) : LoginUIEvent
    data class OnPasswordUpdate(val password: String) : LoginUIEvent
    data object OnTogglePasswordVisibility : LoginUIEvent
    data object OnLoginClicked : LoginUIEvent
    data object OnRememberMeActive : LoginUIEvent
    data object OnRegisterClicked : LoginUIEvent
    data object OnForgotPasswordClicked : LoginUIEvent
    data object OnBackClicked : LoginUIEvent
    data object OnDismissed : LoginUIEvent
    data object OnGoogleSignInClicked: LoginUIEvent
}