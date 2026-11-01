package com.example.musicapp.domain.exception

sealed class AuthException(override val message: String): Exception(message) {
    class NullTokenException: AuthException(message = "El token recibido es nulo o vacío")
    class UserNotFoundException: AuthException(message = "Usuario no autenticado en almacenamiento local")
}