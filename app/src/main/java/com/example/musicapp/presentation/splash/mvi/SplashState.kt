package com.example.musicapp.presentation.splash.mvi

data class SplashState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)
