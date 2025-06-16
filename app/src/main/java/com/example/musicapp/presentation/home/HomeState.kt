package com.example.musicapp.presentation.home

import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.model.UserDto

data class HomeState(
    val isLoading: Boolean = false,
    val userDto: UserDto = UserDto(),
    val data: HomeResponse = HomeResponse(),
    val error: String? = null
)
