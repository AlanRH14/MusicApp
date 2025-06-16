package com.example.musicapp.presentation.home

import com.example.musicapp.data.model.HomeResponseDto
import com.example.musicapp.data.model.User

data class HomeState(
    val isLoading: Boolean = false,
    val user: User = User(),
    val data: HomeResponseDto = HomeResponseDto(),
    val error: String? = null
)
