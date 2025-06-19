package com.example.musicapp.presentation.home

sealed class HomeEffect {
    data class ShowErrorMessage(val message: String) : HomeEffect()
}