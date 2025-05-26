package com.example.musicapp.presentation.home

sealed class HomeEvent {
    data class ShowErrorMessage(val message: String) : HomeEvent()
}