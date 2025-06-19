package com.example.musicapp.common

sealed interface Resource<out T> {
    data object Loading : Resource<Nothing>
    data class Success<T>(val data: T) : Resource<T>
    data class Error<T>(
        val message: String,
        val throwable: Throwable? = null,
        val data: T? = null
    ) : Resource<T>
}