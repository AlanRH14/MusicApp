package com.example.musicapp.domain.repository

import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.utils.Resource

interface HomeRepository {

    suspend fun getHomeData(): Resource<HomeResponse>
}