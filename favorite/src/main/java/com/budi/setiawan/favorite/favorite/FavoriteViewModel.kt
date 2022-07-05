package com.budi.setiawan.favorite.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.budi.setiawan.core.domain.usecase.GameUseCase

class FavoriteViewModel (gameUseCase: GameUseCase) : ViewModel() {
    val favoriteGames = gameUseCase.getFavoriteGames().asLiveData()
}