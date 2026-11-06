package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.response.HomeResponse
import com.example.musicapp.data.remote.api.HomeApiService
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.common.Resource
import com.example.musicapp.data.remote.api.utils.safeFlowApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val homeApiService: HomeApiService,
    private val apiHomeMapper: Mapper<HomeResponse, Home>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HomeRepository {

    override fun getHomeData(): Flow<Resource<Home>> =
        safeFlowApiCall(
            ioDispatcher = ioDispatcher,
            apiCall = { homeApiService.getHome() },
            transform = { res ->
                apiHomeMapper.mapTo(input = res)
            }
        )
}
