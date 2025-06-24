package com.example.musicapp.domain.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val artist: Artist,
    val audioUrl: String,
    val coverImage: String,
    val createdAt: Long,
    val duration: Int,
    val genre: String,
    val id: String,
    val releaseDate: Long,
    val title: String,
    val updateAt: Long,
)

