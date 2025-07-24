package com.example.musicapp.presentation.login.mvi

sealed interface LoginEffect {
    data class ShowErrorMessage(val message: String) : LoginEffect
    data object NavigateToRegister : LoginEffect
    data object NavigationToBack : LoginEffect
    data object NavigateToHome : LoginEffect
}