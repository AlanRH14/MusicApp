package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.remote.api.HomeApiService
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.domain.exception.AuthException
import com.example.musicapp.utils.Constants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class HomeRepositoryImpl(
    private val homeApiService: HomeApiService,
    private val apiHomeMapper: Mapper<HomeResponse, Home>,
    private val userLocalDataSource: UserLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HomeRepository {

    override fun getHomeData(): Flow<Resource<Home>> = flow {
        emit(Resource.Loading)

        val userData = userLocalDataSource.getUser()
            ?: throw AuthException.UserNotFoundException()

        val response =
            homeApiService.getHome(token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}")

        if (response.isSuccessful) {
            val homeData = response.body()?.let { res ->
                apiHomeMapper.mapTo(apiDto = res)
            } ?: Home()
            emit(Resource.Success(data = homeData))
        } else {
            throw Exception(response.message())
        }
    }.catch { error ->
        emit(Resource.Error(throwable = error))
    }.flowOn(ioDispatcher)
}
