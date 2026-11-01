package com.example.musicapp.utils

import org.koin.core.qualifier.named

object QualifiersConstants {
    val LOGIN_MAPPER = named("LoginApiMapper")
    val USER_MAPPER = named("UserApiMapper")
    val HOME_MAPPER = named("HomeApiMapper")
    val ARTIST_MAPPER = named("ArtistApiMapper")
    val ALBUM_MAPPER = named("AlbumApiMapper")
    val SONG_MAPPER = named("SongApiMapper")
    val PLAYLIST_MAPPER = named("PlaylistApiMapper")
    val DISPATCHERS_IO = named("IODispatchers")
}
