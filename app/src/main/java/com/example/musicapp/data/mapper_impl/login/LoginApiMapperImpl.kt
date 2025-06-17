package com.example.musicapp.data.mapper_impl.login

import com.example.musicapp.common.music.ApiMapper
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.domain.model.Login

class LoginApiMapperImpl: ApiMapper<LoginResponse, Login> {

    override fun mapToDomain(apiDto: LoginResponse): Login {
        return Login(
            token = apiDto.token ?: "",
            user = us

        )
    }
}