package com.example.musicapp.presentation.home

import com.example.musicapp.data.model.HomeResponseDto
import com.example.musicapp.data.model.UserDto

data class HomeState(
    val isLoading: Boolean = false,
    val userDto: UserDto = UserDto(),
    val data: HomeResponseDto = HomeResponseDto(),
    val error: String? = null
)
