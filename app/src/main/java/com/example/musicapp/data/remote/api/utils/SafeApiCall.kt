package com.example.musicapp.data.remote.api.utils

import com.example.musicapp.common.Resource
import com.example.musicapp.domain.exception.NetworkException
import io.ktor.utils.io.CancellationException
import retrofit2.Response

suspend inline fun <T, R> safeApiCall(
    crossinline apiCall: suspend () -> Response<T>,
    crossinline transform: (T) -> R
): Resource<R> {
    return try {
        val response = apiCall()

        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                Resource.Success(data = transform(body))
            } else {
                Resource.Error(throwable = NetworkException.EmptyBody())
            }
        } else {
            Resource.Error(throwable = Exception("Error: ${response.code()} - ${response.message()}"))
        }
    } catch (error : CancellationException) {
        throw error
    } catch (error: Exception) {
        Resource.Error(throwable = Exception(error))
    }
}
