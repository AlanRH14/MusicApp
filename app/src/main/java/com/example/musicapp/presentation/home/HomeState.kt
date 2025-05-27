package com.example.musicapp.presentation.home

data class HomeState(
    val isLoading: Boolean = true,
    val data: List<String> = emptyList(),
    val error: String? = null
)
