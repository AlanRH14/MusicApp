package com.example.musicapp.presentation.register

sealed interface RegisterUIEffect {
    data class OnNameUpdate(val name: String) : RegisterUIEffect
    data class OnEmailUpdate(val email: String) : RegisterUIEffect
    data class OnPasswordUpdate(val password: String) : RegisterUIEffect
    data class OnConfirmPasswordUpdate(val confirmPassword: String) : RegisterUIEffect
    data object OnBackClicked : RegisterUIEffect
    data object OnRegisterClicked : RegisterUIEffect
    data object OnLoginClicked : RegisterUIEffect
}