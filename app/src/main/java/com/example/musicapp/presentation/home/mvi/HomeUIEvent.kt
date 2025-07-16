package com.example.musicapp.presentation.home.mvi

sealed interface HomeUIEvent {
    data object GetHomeData : HomeUIEvent
    data class OnSongClicked(val songID: String) : HomeUIEvent
    data class OnAlbumClicked(val albumID: String) : HomeUIEvent
    data class OnSongRecommendationClicked(val songID: String) : HomeUIEvent
    data object OnPlaylistClicked: HomeUIEvent
}