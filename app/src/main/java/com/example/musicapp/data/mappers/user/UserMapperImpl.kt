package com.example.musicapp.data.mappers.user

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.domain.model.User

class UserMapperImpl : Mapper<UserEntity, User> {

    override fun mapTo(input: UserEntity): User {
        return User(
            token = input.token ?: "",
            id = input.id,
            email = input.email ?: "",
            name = input.name ?: "",
            profilePicture = input.profilePicture ?: "",
            createdAt = input.updateAt ?: 0L,
            updateAt = input.updateAt ?: 0L,
        )
    }
}
