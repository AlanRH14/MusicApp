package com.example.musicapp.presentation.login

sealed class LoginEvent {
    data class ShowErrorMessage(val message: String): LoginEvent()
}