package com.budi.setiawan.core.domain.usecase

import com.budi.setiawan.core.data.Resource
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.domain.repository.IGameRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(private val gameRepository: IGameRepository): GameUseCase {
    override fun getAllGames() = gameRepository.getAllGames()
    override fun getGamesId(id: Int): Flow<Resource<Game>> = gameRepository.getGamesId(id)
    override fun getFavoriteGames(): Flow<List<Game>> = gameRepository.getFavoriteGames()
    override fun setFavoriteGames(game: Game, state: Boolean) = gameRepository.setFavoriteGames(game, state)
}