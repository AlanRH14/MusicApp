package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.response.HomeResponse
import com.example.musicapp.data.remote.api.HomeApiService
import com.example.musicapp.domain.model.Home
import com.example.musicapp.domain.repository.HomeRepository
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.preferences.ConstantsPreferences
import com.example.musicapp.data.remote.api.utils.safeFlowApiCall
import com.example.musicapp.domain.exception.AuthException
import com.example.musicapp.domain.repository.DataStoreHandle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class HomeRepositoryImpl(
    private val homeApiService: HomeApiService,
    private val dataStore: DataStoreHandle,
    private val apiHomeMapper: Mapper<HomeResponse, Home>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HomeRepository {

    override fun getHomeData(): Flow<Resource<Home>> =
        safeFlowApiCall(
            ioDispatcher = ioDispatcher,
            apiCall = {
                val token = dataStore.readState(ConstantsPreferences.AuthTokenPreferences).first()
                if (token.isEmpty()) {
                    throw AuthException.NullTokenException()
                }
                homeApiService.getHome()
            },
            transform = { res ->
                apiHomeMapper.mapTo(input = res)
            }
        )
}
