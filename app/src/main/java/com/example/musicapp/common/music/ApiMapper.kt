package com.example.musicapp.common.music

interface ApiMapper<Entity, Domain> {
    fun mapToDomain(apiDto: Entity): Domain
}