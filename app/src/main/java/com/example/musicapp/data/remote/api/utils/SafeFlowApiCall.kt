package com.example.musicapp.data.remote.api.utils

import com.example.musicapp.common.Resource
import com.example.musicapp.domain.exception.NetworkException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

inline fun <T, R> safeFlowApiCall(
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
    crossinline apiCall: suspend () -> Response<T>,
    crossinline transform: (T) -> (R)
): Flow<Resource<R>> = flow {
    emit(Resource.Loading)

    val response = apiCall()
    if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
            emit(Resource.Success(transform(body)))
        } else {
            throw NetworkException.EmptyBody()
        }
    } else {
        throw NetworkException.Http(
            code = response.code(),
            message = response.getErrorMessage()
        )
    }
}.catch { error ->
    emit(Resource.Error(throwable = error))
}.flowOn(ioDispatcher)