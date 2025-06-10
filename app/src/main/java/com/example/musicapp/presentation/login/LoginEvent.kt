package com.example.musicapp.presentation.login

sealed class LoginEvent {
    data class ShowErrorMessage(val message: String) : LoginEvent()
    data object NavigationToRegister : LoginEvent()
    data object NavigationToBack : LoginEvent()
    data object NavigateToHome : LoginEvent()
    data object EmptyEmail: LoginEvent()
    data object EmptyPassword: LoginEvent()
}