package com.example.musicapp.data.remote.repository

import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.utils.ResponseState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StatusRepositoryImpl(
    private val apiService: ApiService
) {

    fun getStatus(): Flow<ResponseState<String>> = flow {
        emit(ResponseState.Loading)

        apiService.getSomething().body()?.get("status")?.let {
            emit(ResponseState.Success(it))
        } ?: emit(ResponseState.Error(Throwable("Failed")))
    }
}