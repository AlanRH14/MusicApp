package com.example.musicapp.presentation.home

sealed interface HomeUIEvent {
    data object GetHomeData: HomeUIEvent
}