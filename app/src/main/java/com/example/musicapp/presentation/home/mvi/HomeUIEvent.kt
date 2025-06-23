package com.example.musicapp.presentation.home.mvi

sealed interface HomeUIEvent {
    data object GetHomeData : HomeUIEvent
    data class OnSongClicked(val id: String) : HomeUIEvent
    data class OnAlbumClicked(val id: String) : HomeUIEvent
    data class OnSongRecommendationClicked(val id: String) : HomeUIEvent
}