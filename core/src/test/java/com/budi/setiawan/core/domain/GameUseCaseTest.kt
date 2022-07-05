package com.budi.setiawan.core.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.budi.setiawan.core.MainCoroutineScope
import com.budi.setiawan.core.data.Resource
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.domain.repository.IGameRepository
import com.budi.setiawan.core.domain.usecase.GameInteractor
import com.budi.setiawan.core.domain.usecase.GameUseCase
import com.budi.setiawan.core.factory.GameFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
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
class GameUseCaseTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineScope = MainCoroutineScope()

    private val fakeGameList = GameFactory.makeGameList(10)
    private val fakeGame = fakeGameList[0]
    private lateinit var gameUseCase: GameUseCase

    @Mock
    private lateinit var gameRepository: IGameRepository

    @Mock
    private lateinit var gamesObserver: Observer<Resource<List<Game>>>

    @Mock
    private lateinit var gameObserver: Observer<Resource<Game>>

    @Mock
    private lateinit var gamesObserverList: Observer<List<Game>>

    @Captor
    private lateinit var captorGames: ArgumentCaptor<Resource<List<Game>>>

    @Captor
    private lateinit var captorGame: ArgumentCaptor<Resource<Game>>

    @Captor
    private lateinit var captorGamesList: ArgumentCaptor<List<Game>>

    /**
     * Run before start testing
     */
    @Before
    fun setUp() {
        gameUseCase = GameInteractor(gameRepository)
    }

    @Test
    fun getGames() = runTest {
        val flow: Flow<Resource<List<Game>>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeGameList))
        }

        Mockito.`when`(gameRepository.getAllGames()).thenReturn(flow)
        val gameList = gameRepository.getAllGames().asLiveData()
        gameList.observeForever(gamesObserver)

        Mockito.verify(gamesObserver).onChanged(captorGames.capture())
        assertEquals(true, captorGames.value is Resource.Loading)

        advanceUntilIdle()

        Mockito.verify(gamesObserver, Mockito.times(2))
            .onChanged(captorGames.capture())
        assertEquals(
            fakeGameList,
            captorGames.value.data
        )
    }

    @Test
    fun getFavoriteGames() = runTest {
        val favoritedGame = fakeGameList.filter { game -> game.isFavorite }
        val flow: Flow<List<Game>> = flow { emit(favoritedGame) }

        Mockito.`when`(gameRepository.getFavoriteGames()).thenReturn(flow)
        val gameList = gameRepository.getFavoriteGames().asLiveData()
        gameList.observeForever(gamesObserverList)

        Mockito.verify(gamesObserverList).onChanged(captorGamesList.capture())
        assertEquals(
            favoritedGame,
            captorGamesList.value
        )
    }

    @Test
    fun getDetailGame() = runTest {
        val flow: Flow<Resource<Game>> = flow {
            emit(Resource.Loading())
            delay(10)
            emit(Resource.Success(fakeGame))
        }

        Mockito.`when`(gameRepository.getGamesId(fakeGame.id)).thenReturn(flow)
        val gameList = gameRepository.getGamesId(fakeGame.id).asLiveData()
        gameList.observeForever(gameObserver)

        Mockito.verify(gameObserver).onChanged(captorGame.capture())
        assertEquals(true, captorGame.value is Resource.Loading)

        advanceUntilIdle()

        Mockito.verify(gameObserver, Mockito.times(2))
            .onChanged(captorGame.capture())
        assertEquals(
            fakeGame,
            captorGame.value.data
        )
    }
}