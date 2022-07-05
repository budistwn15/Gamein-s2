package com.budi.setiawan.core.data.local

import com.budi.setiawan.core.data.source.local.entity.GameEntity
import com.budi.setiawan.core.data.source.local.mapper.GameMapper
import com.budi.setiawan.core.domain.model.Game
import com.budi.setiawan.core.factory.GameFactory
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GameMapperTest {
    private lateinit var gameMapper: GameMapper

    @Before
    fun setUp() {
        gameMapper = GameMapper()
    }

    @Test
    fun mapFromEntity() {
        val gameEntity = GameFactory.makeGameEntity()
        val game = gameMapper.mapFromEntity(gameEntity)

        assertGameDataEquality(gameEntity, game)
    }


    @Test
    fun mapToEntity() {
        val game = GameFactory.makeGame()
        val gameEntity = gameMapper.mapToEntity(game)

        assertGameDataEquality(gameEntity, game)
    }

    @Test
    fun mapFromEntities() {
        val gameEntities = GameFactory.makeGameEntities(2)
        val gameList = gameMapper.mapFromEntities(gameEntities)

        repeat(gameEntities.size) {
            assertGameDataEquality(gameEntities[it], gameList[it])
        }
    }

    private fun assertGameDataEquality(entity: GameEntity, data: Game) {
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
        assertEquals(entity.genres, data.genres)
        assertEquals(entity.isFavorite, data.isFavorite)
    }
}