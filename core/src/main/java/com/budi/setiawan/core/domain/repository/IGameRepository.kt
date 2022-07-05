package com.budi.setiawan.core.domain.repository

import com.budi.setiawan.core.data.Resource
import com.budi.setiawan.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGames(): Flow<Resource<List<Game>>>

    fun getGamesId(id: Int): Flow<Resource<Game>>

    fun getFavoriteGames(): Flow<List<Game>>

    fun setFavoriteGames(game: Game, state: Boolean)
}