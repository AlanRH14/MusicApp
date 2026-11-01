package com.example.musicapp.data.mappers.user

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.data.model.reponse.LoginResponse

class UserEntityMapperImpl : Mapper<LoginResponse, UserEntity> {

    override fun mapTo(input: LoginResponse): UserEntity {
        return UserEntity(
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
