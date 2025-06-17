package com.example.musicapp.data.remote.repository

import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.utils.Resource
import org.koin.core.annotation.Single

@Single
class HomeRepositoryImpl(
    private val apiService: ApiService
): HomeRepository {

    override suspend fun getHomeData(): Resource<HomeResponse> {
        Resource.Loading

        return try {
            val response = apiService.getHome()
            if (response.isSuccessful) {
                response.body()?.let { res ->
                    Resource.Success(data = res)
                } ?: Resource.Success(data = response)
            }
            Resource.Success(data = HomeResponse())
        } catch (e: Exception) {
            Resource.Error("")
        }
    }
}