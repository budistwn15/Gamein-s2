package com.budi.setiawan.favorite.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.domain.usecase.GameUseCase
import com.budi.setiawan.favorite.MainCoroutineScope
import com.budi.setiawan.favorite.factory.GameFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScope()

    private val fakeGameList = GameFactory.makeGameList(10)
    private val favoriteGame = fakeGameList.filter { game -> game.isFavorite }
    private lateinit var favoriteViewModel: FavoriteViewModel

    @Mock
    private lateinit var gameUseCase: GameUseCase

    @Mock
    private lateinit var gamesObserver: Observer<List<Game>>

    @Captor
    private lateinit var captorGames: ArgumentCaptor<List<Game>>

    @Before
    fun setUp() {
        val flow: Flow<List<Game>> = flow {
            emit(favoriteGame)
        }

        Mockito.`when`(gameUseCase.getFavoriteGames()).thenReturn(flow)
        favoriteViewModel = FavoriteViewModel(gameUseCase)
        Mockito.verify(gameUseCase).getFavoriteGames()
    }

    @Test
    fun getFavoriteGames() {
        val liveData = favoriteViewModel.favoriteGames
        liveData.observeForever(gamesObserver)

        Mockito.verify(gamesObserver).onChanged(captorGames.capture())
        Assert.assertNotNull(captorGames.value)
        Assert.assertEquals(favoriteGame, captorGames.value)
    }
}