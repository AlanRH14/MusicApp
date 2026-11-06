package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.Mapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.preferences.ConstantsPreferences
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.data.model.request.UpdatePlaylistSongRequest
import com.example.musicapp.data.remote.api.PlaylistApiService
import com.example.musicapp.data.remote.api.getErrorMessage
import com.example.musicapp.data.remote.api.safeApiCall
import com.example.musicapp.domain.exception.AuthException
import com.example.musicapp.domain.exception.NetworkException
import com.example.musicapp.domain.model.ActionMessage
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.model.PlaylistData
import com.example.musicapp.domain.repository.DataStoreHandle
import com.example.musicapp.domain.repository.PlaylistRepository
import com.example.musicapp.utils.constants.AppConstants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class PlaylistRepositoryImpl(
    private val apiService: PlaylistApiService,
    private val dataStore: DataStoreHandle,
    private val apiPlaylistMapper: Mapper<PlaylistDto, Playlist>,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlaylistRepository {

    override fun getPlaylist(): Flow<Resource<List<Playlist>>> = flow {
        emit(Resource.Loading)

        val token = dataStore.readState(ConstantsPreferences.AuthTokenPreferences).first()
        if (token.isEmpty()) {
            throw AuthException.UserNotFoundException()
        }

        val response =
            apiService.getPlaylist(token = "$AUTHENTICATION_HEADER_TYPE $token")

        if (response.isSuccessful) {
            val playlistData = response.body()?.let { res ->
                res.map { apiPlaylistMapper.mapTo(input = it) }
            } ?: emptyList()
            emit(Resource.Success(data = playlistData))
        } else {
            throw NetworkException.Http(
                code = response.code(),
                message = response.getErrorMessage()
            )
        }
    }.catch { error ->
        emit(Resource.Error(throwable = error))
    }.flowOn(ioDispatcher)

    override suspend fun createPlaylist(playlistData: PlaylistData): Resource<Playlist> =
        withContext(ioDispatcher) {
            safeApiCall(
                apiCall = {
                    val token = dataStore.readState(ConstantsPreferences.AuthTokenPreferences).first()
                    if (token.isEmpty()) {
                        throw AuthException.UserNotFoundException()
                    }
                    apiService.createPlaylist(
                        token = "$AUTHENTICATION_HEADER_TYPE $token",
                        playlistRequest = CreatePlaylistRequest(
                            name = playlistData.name,
                            description = playlistData.description,
                            coverImage = playlistData.coverImage,
                            songIds = playlistData.songIds
                        )
                    )
                },
                transform = { playlistData ->
                    apiPlaylistMapper.mapTo(input = playlistData)
                }
            )
        }

    override suspend fun addSongToPlaylist(
        playlistID: String,
        songID: String
    ): Resource<ActionMessage> =
        withContext(ioDispatcher) {
            safeApiCall(
                apiCall = {
                    val token =
                        dataStore.readState(ConstantsPreferences.AuthTokenPreferences).first()
                    if (token.isEmpty()) {
                        throw AuthException.UserNotFoundException()
                    }
                    apiService.addSongToPlaylist(
                        token = "$AUTHENTICATION_HEADER_TYPE $token",
                        playlistId = playlistID,
                        request = UpdatePlaylistSongRequest(songIds = listOf(songID))
                    )
                },
                transform = { ActionMessage(message = "Song added to playlist successfully") }
            )
        }

    override suspend fun deleteSongFromPlaylist(
        playlistID: String,
        songID: String
    ): Resource<ActionMessage> = withContext(ioDispatcher) {
        safeApiCall(
            apiCall = {
                val token = dataStore.readState(ConstantsPreferences.AuthTokenPreferences).first()
                if (token.isEmpty()) {
                    throw AuthException.UserNotFoundException()
                }
                apiService.removeSongsFromPlaylist(
                    token = "$AUTHENTICATION_HEADER_TYPE $token",
                    playlistId = playlistID,
                    request = UpdatePlaylistSongRequest(songIds = listOf(songID))
                )
            },
            transform = {
                ActionMessage(message = "Song deleted successfully")
            }
        )
    }

    override fun getPlaylistDetails(playlistID: String): Flow<Resource<Playlist>> = flow {
        emit(Resource.Loading)

        val token = dataStore.readState(ConstantsPreferences.AuthTokenPreferences).first()
        if (token.isEmpty()) {
            throw AuthException.UserNotFoundException()
        }

        val response = apiService.getPlaylistById(
            token = "$AUTHENTICATION_HEADER_TYPE $token",
            id = playlistID
        )

        if (response.isSuccessful) {
            val playlistData = response.body()?.let { res ->
                apiPlaylistMapper.mapTo(input = res)
            } ?: Playlist()
            emit(Resource.Success(data = playlistData))
        } else {
            val errorBody = response.errorBody()?.string()
            val errorMessage = errorBody.takeIf { !it.isNullOrEmpty() } ?: response.message()
            throw Exception(errorMessage)
        }
    }.catch { error ->
        emit(Resource.Error(throwable = error))
    }.flowOn(ioDispatcher)
}
