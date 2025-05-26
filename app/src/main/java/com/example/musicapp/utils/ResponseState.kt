package com.example.musicapp.utils

sealed class ResponseState<out T> {
    data object Loading : ResponseState<Nothing>()
    data class Success<T>(val data: T) : ResponseState<T>()
    data class Error<T>(val message: Throwable) : ResponseState<T>()
}