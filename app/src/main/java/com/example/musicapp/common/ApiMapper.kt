package com.example.musicapp.common

interface ApiMapper<ApiDto, Domain> {
    fun mapTo(apiDto: ApiDto): Domain
}
