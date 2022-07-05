package com.budi.setiawan.core.data

import com.budi.setiawan.core.data.source.local.LocalDataSource
import com.budi.setiawan.core.data.source.remote.RemoteDataSource
import com.budi.setiawan.core.data.source.remote.network.ApiResponse
import com.budi.setiawan.core.data.source.remote.response.GameResponse
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.domain.repository.IGameRepository
import com.budi.setiawan.core.utils.AppExecutors
import com.budi.setiawan.core.utils.DataMapper
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
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<GameResponse>>> = remoteDataSource.getAllGames()

            override suspend fun saveCallResult(data: List<GameResponse>) {
                val games = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGameAll(games)
            }
        }.asFlow()

    override fun getGamesId(id: Int): Flow<Resource<Game>> =
        object: NetworkBoundResource<Game, GameResponse>(){
            override fun loadFromDB(): Flow<Game> {
                return localDataSource.getGameById(id).map {
                    DataMapper.mapFromEntity(it)
                }
            }

            override fun shouldFetch(data: Game?): Boolean = data?.description.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<GameResponse>> {
                return remoteDataSource.getGamesDetail(id)
            }

            override suspend fun saveCallResult(data: GameResponse) {
                val game = DataMapper.mapResponsesToEntity(data)
                localDataSource.insertGame(game)
            }
        }.asFlow()

    override fun getFavoriteGames(): Flow<List<Game>> {
        return localDataSource.getFavoriteGames().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteGames(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute{
            localDataSource.setFavoriteGames(gameEntity,state)
        }
    }
}