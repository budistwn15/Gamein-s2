package com.budi.setiawan.gamein.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameUseCase: GameUseCase): ViewModel() {
    fun setFavoriteGames(game: Game, newStatus: Boolean){
        gameUseCase.setFavoriteGames(game, newStatus)
    }
    fun getGamesId(id: Int) = gameUseCase.getGamesId(id).asLiveData()
}