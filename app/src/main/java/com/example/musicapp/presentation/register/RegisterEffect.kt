package com.example.musicapp.presentation.register

sealed class RegisterEffect {
    data class ShowErrorMessage(val message: String) : RegisterEffect()
    data object NavigateToLogin : RegisterEffect()
}