package com.example.musicapp.domain.exception

sealed class NetworkException(override val message: String): Exception(message) {
    class EmptyBody: NetworkException(message = "El cuerpo de la respuesta está vacío")
    class Http(val code: Int, message: String): NetworkException(message = "Error $code: $message")
}
