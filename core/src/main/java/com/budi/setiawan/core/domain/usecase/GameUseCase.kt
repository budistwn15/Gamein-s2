package com.budi.setiawan.core.domain.usecase

import com.budi.setiawan.core.data.Resource
import com.budi.setiawan.core.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGames(): Flow<Resource<List<Game>>>
    fun getGamesId(id: Int): Flow<Resource<Game>>
    fun getFavoriteGames(): Flow<List<Game>>
    fun setFavoriteGames(game: Game, state: Boolean)
}