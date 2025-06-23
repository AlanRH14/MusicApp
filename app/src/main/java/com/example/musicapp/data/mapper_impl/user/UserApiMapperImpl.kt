package com.example.musicapp.data.mapper_impl.user

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.domain.model.User

class UserApiMapperImpl : ApiMapper<UserEntity, User> {

    override fun mapToDomain(apiDto: UserEntity): User {
        return User(
            token = apiDto.token ?: "",
            id = apiDto.id ?: "",
            email = apiDto.email ?: "",
            name = apiDto.name ?: "",
            profilePicture = apiDto.profilePicture ?: "",
            createdAt = apiDto.updateAt ?: 0L,
            updateAt = apiDto.updateAt ?: 0L,
        )
    }
}