package com.example.musicapp.presentation.home.mvi

sealed interface HomeUIEvent {
    data object GetHomeData: HomeUIEvent
    data class OnClicked
}