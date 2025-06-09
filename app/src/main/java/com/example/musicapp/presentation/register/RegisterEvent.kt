package com.example.musicapp.presentation.register

sealed class RegisterEvent {
    data class ShowErrorMessage(val message: String) : RegisterEvent()
    data object NavigateToLogin : RegisterEvent()
}