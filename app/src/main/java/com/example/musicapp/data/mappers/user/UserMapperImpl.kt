package com.example.musicapp.data.mappers.user

import com.example.musicapp.common.Mapper
import com.example.musicapp.data.local.database.entities.UserEntity
import com.example.musicapp.domain.model.User

class UserEntityMapperImpl : Mapper<User, UserEntity> {

    override fun mapTo(input: User): UserEntity {
        return UserEntity(
            token = input.token,
            id = input.id,
            email = input.email,
            name = input.name,
            profilePicture = input.profilePicture,
            createdAt = input.updateAt,
            updateAt = input.updateAt,
        )
    }
}
