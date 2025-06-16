package com.example.musicapp.presentation.home

import com.example.musicapp.data.model.HomeResponse

data class HomeState(
    val isLoading: Boolean = false,
    val data: HomeResponse = HomeResponse(),
    val error: String? = null
)
