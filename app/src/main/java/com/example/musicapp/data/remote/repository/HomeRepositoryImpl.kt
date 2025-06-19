package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.common.Resource

class HomeRepositoryImpl(
    private val apiService: ApiService,
    private val apiHomeMapper: ApiMapper<HomeResponse, Home>
) : HomeRepository {

    override suspend fun getHomeData(): Resource<Home> {
        Resource.Loading

        return try {
            val response = apiService.getHome()
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    Resource.Success(data = apiHomeMapper.mapToDomain(apiDto = res))
                } ?: Resource.Success(data = Home())
            }
            Resource.Success(data = Home())
        } catch (e: Exception) {
            Resource.Error("")
        }
    }
}