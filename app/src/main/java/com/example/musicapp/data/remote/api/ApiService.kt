package com.example.musicapp.data.remote.api

import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.SongDto
import com.example.musicapp.data.model.reponse.HomeResponse
import com.example.musicapp.data.model.request.LoginRequest
import com.example.musicapp.data.model.reponse.LoginResponse
import com.example.musicapp.data.model.reponse.UpdatePlaylistSongResponse
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.data.model.request.RegisterRequest
import com.example.musicapp.data.model.request.UpdatePlaylistSongRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): Response<LoginResponse>

    @GET("/home")
    suspend fun getHome(
        @Header("Authorization") token: String
    ): Response<HomeResponse>

    @GET("/songs/{id}")
    suspend fun getSongByID(
        @Header("Authorization") token :String,
        @Path("id") id: String
    ): Response<SongDto>

    @GET("/playlists")
    suspend fun getPlaylist(
        @Header("Authorization") token: String
    ): Response<List<PlaylistDto>>

    @POST("/playlists")
    suspend fun createPlaylist(
        @Header("Authorization") token: String,
        @Body playlistRequest: CreatePlaylistRequest
    ): Response<PlaylistDto>

    @POST("/playlist/{Id}/songs")
    suspend fun addSongToPlaylist(
        @Path("Id") playlistId: String,
        @Body request: UpdatePlaylistSongRequest
    ): Response<UpdatePlaylistSongResponse>
}