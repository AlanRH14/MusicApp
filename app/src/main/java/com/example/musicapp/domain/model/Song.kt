package com.example.musicapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val artist: Artist = Artist(),
    val audioUrl: String = "",
    val coverImage: String,
    val createdAt: Long,
    val duration: Int,
    val genre: String,
    val id: String,
    val releaseDate: Long,
    val title: String,
    val updateAt: Long,
): Parcelable
