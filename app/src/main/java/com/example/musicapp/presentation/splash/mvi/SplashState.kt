package com.example.musicapp.presentation.splash.mvi

import com.example.musicapp.navigation.NavRoutes

data class SplashState(
    val isLoading: Boolean = true,
    val destination: NavRoutes = NavRoutes.OnboardingRoute,
    val errorMessage: String? = null
)
