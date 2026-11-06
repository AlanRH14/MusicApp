package com.example.musicapp.data.remote.api

import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.response.UpdatePlaylistSongResponse
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.data.model.request.UpdatePlaylistSongRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface PlaylistApiService {
    @GET("/playlists")
    suspend fun getPlaylist(): Response<List<PlaylistDto>>

    @POST("/playlists")
    suspend fun createPlaylist(
        @Body playlistRequest: CreatePlaylistRequest
    ): Response<PlaylistDto>

    @POST("/playlists/{id}/songs")
    suspend fun addSongToPlaylist(
        @Path("id") playlistId: String,
        @Body request: UpdatePlaylistSongRequest
    ): Response<UpdatePlaylistSongResponse>

    @HTTP(method = "DELETE", path = "playlists/{id}/songs", hasBody = true)
    suspend fun removeSongsFromPlaylist(
        @Path("id") playlistId: String,
        @Body request: UpdatePlaylistSongRequest
    ): Response<UpdatePlaylistSongResponse>

    @GET("/playlists/{id}")
    suspend fun getPlaylistById(@Path("id") id: String): Response<PlaylistDto>
}
