package com.budi.setiawan.core.data.source.local.room

import androidx.room.*
import com.budi.setiawan.core.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {

    @Query("SELECT * FROM tb_games")
    fun getAllGames(): Flow<List<GameEntity>>

    @Query("SELECT * FROM tb_games WHERE id=:id")
    fun getGamesById(id: Int): Flow<GameEntity>

    @Query("SELECT * FROM tb_games WHERE isFavorite = 1")
    fun getFavoriteGames(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGameAll(game: List<GameEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: GameEntity)

    @Update
    fun updateFavoriteGames(game: GameEntity)

}