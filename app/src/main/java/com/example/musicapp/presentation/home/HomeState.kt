package com.example.musicapp.presentation.home

data class HomeState(
    val isLoading: Boolean = false,
    val data: List<String> = emptyList()
)
