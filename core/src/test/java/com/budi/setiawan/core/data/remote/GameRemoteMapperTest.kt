package com.budi.setiawan.core.data.remote

import com.budi.setiawan.core.data.source.local.entity.GameEntity
import com.budi.setiawan.core.data.source.remote.response.GameResponse
import com.budi.setiawan.core.factory.GameFactory
import com.budi.setiawan.core.utils.DataMapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GameRemoteMapperTest {
    private lateinit var gameRemoteMapper: DataMapper

    @Before
    fun setUp() {
        gameRemoteMapper = DataMapper
    }

    @Test
    fun mapRemoteToEntity() {
        val gameRemote = GameFactory.makeGameResponse()
        val gameEntity = gameRemoteMapper.mapResponsesToEntity(gameRemote)

        assertGameDataEquality(gameRemote, gameEntity)
    }

    @Test
    fun mapRemoteToEntities() {
        val gameRemoteList = GameFactory.makeGameResponseList(2)
        val gameEntities = gameRemoteMapper.mapResponsesToEntities(gameRemoteList)

        repeat(gameRemoteList.size) {
            assertGameDataEquality(gameRemoteList[it], gameEntities[it])
        }
    }

    private fun assertGameDataEquality(entity: GameResponse, data: GameEntity) {
        assertEquals(entity.id, data.id)
        assertEquals(entity.name, data.name)
        assertEquals(entity.released, data.released)
        assertEquals(entity.background_image, data.background_image)
        assertEquals(entity.rating, data.rating)
        assertEquals(entity.ratings_count, data.ratings_count)
        assertEquals(entity.reviews_count, data.reviews_count)
        assertEquals(entity.added, data.added)
        assertEquals(entity.description, data.description)
        assertEquals(entity.website, data.website)
    }
}