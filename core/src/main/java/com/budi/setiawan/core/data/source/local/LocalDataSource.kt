package com.budi.setiawan.core.data.source.local

import com.budi.setiawan.core.data.source.local.entity.GameEntity
import com.budi.setiawan.core.data.source.local.room.GameDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val gameDao: GameDao,
){

    fun getAllGames() : Flow<List<GameEntity>> = gameDao.getAllGames()

    fun getGameById(id: Int): Flow<GameEntity> = gameDao.getGamesById(id)

    fun getFavoriteGames(): Flow<List<GameEntity>> = gameDao.getFavoriteGames()

    suspend fun insertGameAll(gameEntity: List<GameEntity>) = gameDao.insertGameAll(gameEntity)

    suspend fun insertGame(gameEntity: GameEntity) = gameDao.insertGame(gameEntity)

    fun setFavoriteGames(gameEntity: GameEntity, newState: Boolean){
        gameEntity.isFavorite = newState
        gameDao.updateFavoriteGames(gameEntity)
    }
}