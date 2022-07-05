package com.budi.setiawan.core.data

import com.budi.setiawan.core.data.source.local.LocalDataSource
import com.budi.setiawan.core.data.source.remote.RemoteDataSource
import com.budi.setiawan.core.data.source.remote.network.ApiResponse
import com.budi.setiawan.core.data.source.remote.response.GameResponse
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.domain.repository.IGameRepository
import com.budi.setiawan.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGameRepository {
    override fun getAllGames(): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GameResponse>>(){
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGames().map {
                    localDataSource.mapper.mapFromEntities(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> = remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                return remoteDataSource.mapper.mapRemoteToEntities(data).let {
                    localDataSource.insertGameAll(it)
                }
            }
        }.asFlow()

    override fun getGamesId(id: Int): Flow<Resource<Game>> =
        object: NetworkBoundResource<Game, GameResponse>(){
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getGameById(id).map {
                    localDataSource.mapper.mapFromEntity(it)
                }
            }

            override fun shouldFetch(data: Game?): Boolean = data?.description.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> {
                return remoteDataSource.getGamesDetail(id)
            }

            override suspend fun saveCallResult(data: GameResponse) {
                return remoteDataSource.mapper.mapRemoteToEntity(data).let {
                    localDataSource.insertGame(it)
                }
            }
        }.asFlow()

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            localDataSource.mapper.mapFromEntities(it)
        }
    }

    override fun setFavoriteGames(game: Game, state: Boolean) {
        val gameEntity = localDataSource.mapper.mapToEntity(game)
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteGames(gameEntity,state)
        }
    }
}