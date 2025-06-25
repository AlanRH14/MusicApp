package com.example.musicapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Song(
    val artist: Artist = Artist(),
    val audioUrl: String = "",
    val coverImage: String = "",
    val createdAt: Long = 0L,
    val duration: Int = 0,
    val genre: String = "",
    val id: String = "",
    val releaseDate: Long = 0L,
    val title: String = "",
    val updateAt: Long = 0L,
) : Parcelable
