package com.example.musicapp.presentation.register.mvi

sealed interface RegisterEffect {
    data object NavigateToLogin : RegisterEffect
    data object NavigateToHome : RegisterEffect
}