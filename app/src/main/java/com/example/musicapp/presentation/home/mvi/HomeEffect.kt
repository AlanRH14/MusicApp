package com.example.musicapp.presentation.home.mvi

sealed class HomeEffect {
    data class ShowErrorMessage(val message: String) : HomeEffect()
}