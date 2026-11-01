package com.example.musicapp.domain

sealed class AuthException(override val message: String): Exception(message) {
    class NullTokenException: AuthException("El token recibido es nulo o vacío")
}