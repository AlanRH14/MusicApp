package com.example.musicapp.data.remote.repository

import com.example.musicapp.common.ApiMapper
import com.example.musicapp.common.Resource
import com.example.musicapp.data.local.datasource.UserLocalDataSource
import com.example.musicapp.data.model.PlaylistDto
import com.example.musicapp.data.model.reponse.UpdatePlaylistSongResponse
import com.example.musicapp.data.model.request.CreatePlaylistRequest
import com.example.musicapp.data.model.request.UpdatePlaylistSongRequest
import com.example.musicapp.data.remote.api.PlaylistApiService
import com.example.musicapp.domain.AuthException
import com.example.musicapp.domain.model.Playlist
import com.example.musicapp.domain.repository.PlaylistRepository
import com.example.musicapp.utils.Constants.AUTHENTICATION_HEADER_TYPE
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class PlaylistRepositoryImpl(
    private val apiService: PlaylistApiService,
    private val apiPlaylistMapper: ApiMapper<PlaylistDto, Playlist>,
    private val userLocalDataSource: UserLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlaylistRepository {

    override fun getPlaylist(): Flow<Resource<List<Playlist>>> = flow {
        emit(Resource.Loading)

        val userData = userLocalDataSource.getUser()
            ?: throw AuthException.UserNotFoundException()

        val response =
            apiService.getPlaylist(token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}")

        if (response.isSuccessful) {
            val playlistData = response.body()?.let { res ->
                res.map { apiPlaylistMapper.mapToDomain(apiDto = it) }
            } ?: emptyList()
            emit(Resource.Success(data = playlistData))
        } else {
            throw Exception(response.message())
        }
    }.catch { error ->
        emit(Resource.Error(throwable = error))
    }.flowOn(ioDispatcher)

    override suspend fun createPlaylist(playlistRequest: CreatePlaylistRequest): Resource<Playlist> =
        withContext(ioDispatcher) {
            try {
                val userData = userLocalDataSource.getUser()
                    ?: return@withContext Resource.Error(throwable = AuthException.UserNotFoundException())

                val response = apiService.createPlaylist(
                    token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}",
                    playlistRequest = playlistRequest
                )

                if (response.isSuccessful) {
                    val playlistData = response.body()?.let { res ->
                        apiPlaylistMapper.mapToDomain(apiDto = res)
                    } ?: Playlist()
                    Resource.Success(data = playlistData)
                } else {
                    Resource.Error(Exception(response.message()))
                }
            } catch (error: Exception) {
                Resource.Error(throwable = error)
            }
        }

    override suspend fun addSongToPlaylist(
        playlistID: String,
        songID: String
    ): Resource<UpdatePlaylistSongResponse> =
        withContext(ioDispatcher) {
            try {
                val userData = userLocalDataSource.getUser()
                    ?: return@withContext Resource.Error(throwable = AuthException.UserNotFoundException())

                val response = apiService.addSongToPlaylist(
                    token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}",
                    playlistId = playlistID,
                    request = UpdatePlaylistSongRequest(songIds = listOf(songID))
                )

                if (response.isSuccessful) {
                    val playlistData = response.body()?.let { res ->
                        res
                    } ?: UpdatePlaylistSongResponse()
                    Resource.Success(data = playlistData)
                } else {
                    Resource.Error(throwable = Exception(response.message()))
                }
            } catch (error: Exception) {
                Resource.Error(throwable = error)
            }
        }

    override suspend fun deleteSongFromPlaylist(
        playlistID: String,
        songID: String
    ): Resource<UpdatePlaylistSongResponse> = withContext(ioDispatcher) {
        try {
            val userData = userLocalDataSource.getUser()
                ?: return@withContext Resource.Error(throwable = AuthException.UserNotFoundException())

            val response = apiService.removeSongsFromPlaylist(
                token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}",
                playlistId = playlistID,
                request = UpdatePlaylistSongRequest(songIds = listOf(songID))
            )

            if (response.isSuccessful) {
                val playlistData = response.body()?.let { res ->
                    res
                } ?: UpdatePlaylistSongResponse()
                Resource.Success(data = playlistData)
            } else {
                Resource.Error(throwable = Exception(response.message()))
            }
        } catch (error: Exception) {
            Resource.Error(throwable = error)
        }
    }

    override fun getPlaylistDetails(playlistID: String): Flow<Resource<Playlist>> = flow {
        emit(Resource.Loading)

        val userData = userLocalDataSource.getUser()
            ?: throw AuthException.UserNotFoundException()

        val response = apiService.getPlaylistById(
            token = "$AUTHENTICATION_HEADER_TYPE ${userData.token}",
            id = playlistID
        )

        if (response.isSuccessful) {
            val playlistData = response.body()?.let { res ->
                apiPlaylistMapper.mapToDomain(apiDto = res)
            } ?: Playlist()
            emit(Resource.Success(data = playlistData))
        } else {
            throw Exception(response.message())
        }
    }.catch { error ->
        emit(Resource.Error(throwable = error))
    }.flowOn(ioDispatcher)
}
