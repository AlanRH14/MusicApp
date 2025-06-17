package com.example.musicapp.domain.repository

import com.example.musicapp.domain.model.Home
import com.example.musicapp.utils.Resource

interface HomeRepository {

    suspend fun getHomeData(): Resource<Home>
}