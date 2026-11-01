package com.example.musicapp.navigation

import kotlinx.serialization.Serializable

sealed class NavRoutes {
    @Serializable
    data object OnboardingRoute : NavRoutes()

    @Serializable
    data object LoginRoute : NavRoutes()

    @Serializable
    data object RegisterRoute : NavRoutes()

    @Serializable
    data object HomeRoute : NavRoutes()

    @Serializable
    data class PlaySong(val songID: String) : NavRoutes()

    @Serializable
    data object Playlist : NavRoutes()

    @Serializable
    data object CreatePlaylist : NavRoutes()

    @Serializable
    data class PlaylistDetail(val playlistID: String) : NavRoutes()
}
