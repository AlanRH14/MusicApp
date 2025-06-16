package com.example.musicapp.presentation.home

import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.model.UserDto
import com.example.musicapp.domain.model.User

data class HomeState(
    val isLoading: Boolean = false,
    val userDto: User = User(),
    val data: HomeResponse = HomeResponse(),
    val error: String? = null
)
