package com.example.musicapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.musicapp.presentation.create_playlist.CreatePlaylistScreen
import com.example.musicapp.presentation.home.HomeScreen
import com.example.musicapp.presentation.login.LoginScreen
import com.example.musicapp.presentation.onboarding.OnboardingScreen
import com.example.musicapp.presentation.play_song.PlaySongScreen
import com.example.musicapp.presentation.playlist.PlaylistScreen
import com.example.musicapp.presentation.playlist_detail.PlaylistDetailScreen
import com.example.musicapp.presentation.register.RegisterScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: NavRoutes,
) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable<NavRoutes.OnboardingRoute> {
            OnboardingScreen(navController = navController)
        }

        composable<NavRoutes.LoginRoute> {
            LoginScreen(navController = navController)
        }

        composable<NavRoutes.RegisterRoute> {
            RegisterScreen(navController = navController)
        }

        composable<NavRoutes.HomeRoute> {
            HomeScreen(navController = navController)
        }

        composable<NavRoutes.PlaySong> { songID ->
            val route = songID.toRoute<NavRoutes.PlaySong>().songID
            PlaySongScreen(navController = navController, songID = route)
        }

        composable<NavRoutes.Playlist> {
            PlaylistScreen(navController = navController)
        }

        composable<NavRoutes.CreatePlaylist> {
            CreatePlaylistScreen(navController = navController)
        }

        composable<NavRoutes.PlaylistDetail> { playlistID ->
            val route = playlistID.toRoute<PlaylistDetail>().playlistID
            PlaylistDetailScreen(navController = navController, playlistID = route)
        }
    }
}