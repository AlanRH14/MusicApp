package com.example.musicapp.presentation.register.mvi

sealed interface RegisterUIEvent {
    data class OnNameUpdate(val name: String) : RegisterUIEvent
    data class OnEmailUpdate(val email: String) : RegisterUIEvent
    data class OnPasswordUpdate(val password: String) : RegisterUIEvent
    data class OnConfirmPasswordUpdate(val confirmPassword: String) : RegisterUIEvent
    data object OnTogglePasswordVisibility : RegisterUIEvent
    data object OnToggleConfirmPasswordVisibility : RegisterUIEvent
    data object OnBackClicked : RegisterUIEvent
    data object OnRegisterClicked : RegisterUIEvent
    data object OnLoginClicked : RegisterUIEvent
}