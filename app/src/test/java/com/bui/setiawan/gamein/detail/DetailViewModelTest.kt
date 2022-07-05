package com.bui.setiawan.gamein.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.budi.setiawan.core.data.Resource
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.domain.usecase.GameInteractor
import com.budi.setiawan.gamein.detail.DetailViewModel
import com.bui.setiawan.gamein.MainCoroutineScope
import com.bui.setiawan.gamein.factory.GameFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Assert.assertEquals
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
class DetailViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScope()

    private val fakeGame = GameFactory.makeGame()
    private lateinit var detailViewModel: DetailViewModel

    @Mock
    private lateinit var gameUseCase: GameInteractor

    @Mock
    private lateinit var gamesObserver: Observer<Resource<Game>>

    @Captor
    private lateinit var captorGames: ArgumentCaptor<Resource<Game>>

    @Before
    fun setUp() {
        detailViewModel = DetailViewModel(gameUseCase)
    }

    @Test
    fun getDetailGame() = runTest {
        val flow: Flow<Resource<Game>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeGame))
        }

        Mockito.`when`(gameUseCase.getGamesId(fakeGame.id)).thenReturn(flow)
        val result = detailViewModel.getGamesId(fakeGame.id)
        Mockito.verify(gameUseCase).getGamesId(fakeGame.id)
        result.observeForever(gamesObserver)

        Mockito.verify(gamesObserver).onChanged(captorGames.capture())
        Assert.assertTrue(captorGames.value is Resource.Loading)

        advanceUntilIdle()

        Mockito.verify(gamesObserver, Mockito.times(2))
            .onChanged(captorGames.capture())
        assertEquals(fakeGame, captorGames.value.data)
    }
}