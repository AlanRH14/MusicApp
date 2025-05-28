package com.example.musicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicapp.presentation.onboarding.OnboardingScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: NavRoutes
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable<OnboardingRoute> {
            OnboardingScreen(navController = navController)
        }

        composable<LoginRoute> {  }

        composable<RegisterRoute> {  }
    }
}