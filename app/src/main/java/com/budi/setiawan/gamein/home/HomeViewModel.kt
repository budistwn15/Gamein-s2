package com.budi.setiawan.gamein.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.budi.setiawan.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(gameUseCase: GameUseCase): ViewModel() {
    val games = gameUseCase.getAllGames().asLiveData()
}