package com.example.musicapp.utils

import org.koin.core.qualifier.named

object QualifiersConstants {
    val USER_MAPPER = named("UserMapper")
    val USER_ENTITY_MAPPER = named("UserEntityMapper")
    val HOME_MAPPER = named("HomeApiMapper")
    val ARTIST_MAPPER = named("ArtistApiMapper")
    val ALBUM_MAPPER = named("AlbumApiMapper")
    val SONG_MAPPER = named("SongApiMapper")
    val PLAYLIST_MAPPER = named("PlaylistApiMapper")
    val DISPATCHERS_IO = named("IODispatchers")
}
