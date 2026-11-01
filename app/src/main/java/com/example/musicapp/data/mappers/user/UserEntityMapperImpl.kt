package com.example.musicapp.data.mappers.user

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.domain.model.User

class UserMapperImpl : Mapper<LoginResponse, User> {

    override fun mapTo(input: LoginResponse): User {
        return User(
            token = input.token ?: "",
            id = input.user?.id ?: "",
            email = input.user?.email ?: "",
            name = input.user?.name ?: "",
            profilePicture = input.user?.profilePicture ?: "",
            createdAt = input.user?.updateAt ?: 0L,
            updateAt = input.user?.updateAt ?: 0L,
        )
    }
}
