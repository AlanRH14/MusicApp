package com.example.musicapp.common

interface ApiMapper<ApiDto, Domain> {
    fun mapToDomain(apiDto: ApiDto): Domain
}