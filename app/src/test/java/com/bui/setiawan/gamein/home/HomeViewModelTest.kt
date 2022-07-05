package com.bui.setiawan.gamein.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.budi.setiawan.core.data.Resource
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.domain.usecase.GameInteractor
import com.budi.setiawan.gamein.home.HomeViewModel
import com.bui.setiawan.gamein.MainCoroutineScope
import com.bui.setiawan.gamein.factory.GameFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
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
class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScope()

    private val fakeGameList = GameFactory.makeGameList(10)
    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var gameInteractor: GameInteractor

    @Mock
    private lateinit var gamesObserver: Observer<Resource<List<Game>>>

    @Captor
    private lateinit var captorGames: ArgumentCaptor<Resource<List<Game>>>

    @Before
    fun setUp() {
        val flow: Flow<Resource<List<Game>>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeGameList))
        }

        Mockito.`when`(gameInteractor.getAllGames()).thenReturn(flow)
        homeViewModel = HomeViewModel(gameInteractor)
        Mockito.verify(gameInteractor).getAllGames()
    }

    @Test
    fun getGames() = runTest{

        val gameList = homeViewModel.games
        gameList.observeForever(gamesObserver)

        runCurrent()

        Mockito.verify(gamesObserver).onChanged(captorGames.capture())
        Assert.assertTrue(captorGames.value is Resource.Loading)

        advanceUntilIdle()

        Mockito.verify(gamesObserver, Mockito.times(2))
            .onChanged(captorGames.capture())
        assertEquals(fakeGameList, captorGames.value.data)
    }

}