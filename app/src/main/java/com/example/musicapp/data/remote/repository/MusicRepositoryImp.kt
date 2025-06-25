package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.domain.model.Song
import com.example.musicapp.domain.repository.MusicRepository

class MusicRepositoryImp(): MusicRepository {

    override suspend fun getSongById(): Resource<Song> {
        TODO("Not yet implemented")
    }
}