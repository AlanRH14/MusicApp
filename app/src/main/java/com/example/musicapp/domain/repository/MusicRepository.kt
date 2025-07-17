package com.example.musicapp.domain.repository

import com.example.musicapp.common.Resource
import com.example.musicapp.domain.model.Song
import kotlinx.coroutines.flow.Flow

interface MusicRepository {
    fun getSongById(id: String): Flow<Resource<Song>>
}