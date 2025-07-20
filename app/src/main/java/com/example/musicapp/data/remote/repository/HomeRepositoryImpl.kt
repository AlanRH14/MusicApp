package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.utils.Constants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val apiService: ApiService,
    private val apiHomeMapper: ApiMapper<HomeResponse, Home>,
    private val userLocalDataSource: UserLocalDataSource
) : HomeRepository {

    override fun getHomeData(): Flow<Resource<Home>> = flow {
        emit(Resource.Loading)
        try {
            userLocalDataSource.getUser()?.let { userData ->
                val response = apiService.getHome(token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}")
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        emit(Resource.Success(data = apiHomeMapper.mapToDomain(apiDto = res)))
                    } ?: Resource.Success(data = Home())
                } else {
                    throw Exception(response.message())
                }
            }
        } catch (e: Exception) {
            emit(Resource.Error("Error: ${e.message}"))
        }
    }
}