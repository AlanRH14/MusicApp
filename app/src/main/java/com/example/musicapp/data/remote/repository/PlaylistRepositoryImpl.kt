package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.reponse.UpdatePlaylistSongResponse
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.data.model.request.UpdatePlaylistSongRequest
import com.example.musicapp.data.remote.api.ApiService
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.repository.PlaylistRepository
import com.example.musicapp.utils.Constants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class PlaylistRepositoryImpl(
    private val apiService: ApiService,
    private val apiPlaylistMapper: ApiMapper<PlaylistDto, Playlist>,
    private val userLocalDataSource: UserLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : PlaylistRepository {

    override fun getPlaylist(): Flow<Resource<List<Playlist>>> = flow {
        emit(Resource.Loading)
        try {
            userLocalDataSource.getUser()?.let { userData ->
                val response =
                    apiService.getPlaylist(token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}")
                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        emit(Resource.Success(data = res.map { apiPlaylistMapper.mapToDomain(apiDto = it) }))
                    } ?: emit(Resource.Success(data = emptyList()))
                } else {
                    throw Exception(response.message())
                }
            } ?: throw Exception("Get local user error")
        } catch (e: Exception) {
            emit(Resource.Error(message = "Error: ${e.message}"))
        }
    }

    override suspend fun createPlaylist(playlistRequest: CreatePlaylistRequest): Resource<Playlist> =
        withContext(ioDispatcher) {
            Resource.Loading
            try {
                userLocalDataSource.getUser()?.let { userData ->
                    val response = apiService.createPlaylist(
                        token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}",
                        playlistRequest = playlistRequest
                    )
                    if (response.isSuccessful) {
                        response.body()?.let { res ->
                            Resource.Success(data = apiPlaylistMapper.mapToDomain(apiDto = res))
                        } ?: Resource.Success(data = Playlist())
                    } else {
                        throw Exception(response.message())
                    }
                } ?: throw Exception("Get local user error")
            } catch (e: Exception) {
                Resource.Error(message = "Error: ${e.message}")
            }
        }

    override suspend fun addSongToPlaylist(
        playlistID: String,
        songID: String
    ): Resource<UpdatePlaylistSongResponse> =
        withContext(ioDispatcher) {
            Resource.Loading
            try {
                val response = apiService.addSongToPlaylist(
                    playlistId = playlistID,
                    request = UpdatePlaylistSongRequest(songIds = listOf(songID))
                )

                if (response.isSuccessful) {
                    response.body()?.let { res ->
                        Resource.Success(res)
                    } ?: Resource.Success(UpdatePlaylistSongResponse())
                } else {
                    throw Exception("Add song to playlist")
                }
            } catch (e: Exception) {
                Resource.Error(message = "Error: ${e.message}")
            }
        }

    override suspend fun deleteSongFromPlaylist(
        playlistID: String,
        songID: String
    ): Resource<UpdatePlaylistSongResponse> = withContext(ioDispatcher) {
        Resource.Loading
        try {
            val response = apiService.removeSongsFromPlaylist(
                playlistId = playlistID,
                request = UpdatePlaylistSongRequest(songIds = listOf(songID))
            )

            if (response.isSuccessful) {
                response.body()?.let { res ->
                    Resource.Success(res)
                } ?: Resource.Success(UpdatePlaylistSongResponse())
            } else {
                throw Exception("Delete song from playlist")
            }
        } catch (e: Exception) {
            Resource.Error("Error: ${e.message}")
        }
    }
}