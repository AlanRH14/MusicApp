package com.example.musicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.musicapp.presentation.home.HomeScreen
import com.example.musicapp.presentation.login.LoginScreen
import com.example.musicapp.presentation.onboarding.OnboardingScreen
import com.example.musicapp.presentation.register.RegisterScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: NavRoutes,
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable<OnboardingRoute> {
            OnboardingScreen(navController = navController)
        }

        composable<LoginRoute> {
            LoginScreen(navController = navController)
        }

        composable<RegisterRoute> {
            RegisterScreen(navController = navController)
        }

        composable<HomeRoute> {
            HomeScreen(navController = navController)
        }
    }
}