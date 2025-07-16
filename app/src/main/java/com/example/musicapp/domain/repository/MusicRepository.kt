package com.example.musicapp.domain.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.domain.model.Song

interface MusicRepository {
    suspend fun getSongById(id: String): Resource<Song>
}