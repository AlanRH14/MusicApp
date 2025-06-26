package com.example.musicapp.navigation

import kotlinx.serialization.Serializable

interface NavRoutes

@Serializable
object OnboardingRoute : NavRoutes

@Serializable
object LoginRoute : NavRoutes

@Serializable
object RegisterRoute : NavRoutes

@Serializable
object HomeRoute: NavRoutes

@Serializable
data class PlaySong(val songID: String): NavRoutes