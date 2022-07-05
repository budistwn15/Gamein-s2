package com.budi.setiawan.core.factory

import com.budi.setiawan.core.data.source.local.entity.GameEntity
import com.budi.setiawan.core.data.source.remote.response.GameResponse
import com.budi.setiawan.core.data.source.remote.response.GenreResponse
import com.budi.setiawan.core.domain.model.Game

class GameFactory {
    
    companion object Factory {
        
        fun makeGame(): Game =
            Game(
                FakerFactory.fakerInt(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerDouble(),
                FakerFactory.fakerInt(),
                FakerFactory.fakerInt(),
                FakerFactory.fakerInt(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerBoolean(),
            )

        /**
         * Returns a list of Game
         *
         */
        fun makeGameList(count: Int): List<Game> {
            val gameList = mutableListOf<Game>()
            repeat(count) {
                gameList.add(makeGame())
            }
            return gameList
        }

        fun makeGameEntity(): GameEntity =
            GameEntity(
                FakerFactory.fakerInt(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerDouble(),
                FakerFactory.fakerInt(),
                FakerFactory.fakerInt(),
                FakerFactory.fakerInt(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerBoolean(),
            )

        fun makeGameEntities(count: Int): List<GameEntity> {
            val gameEntities = mutableListOf<GameEntity>()
            repeat(count) {
                gameEntities.add(makeGameEntity())
            }
            return gameEntities
        }

        fun makeGameResponse(): GameResponse =
            GameResponse(
                FakerFactory.fakerInt(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                FakerFactory.fakerDouble(),
                FakerFactory.fakerInt(),
                FakerFactory.fakerInt(),
                FakerFactory.fakerInt(),
                FakerFactory.fakerString(),
                FakerFactory.fakerString(),
                listOf(
                    GenreResponse(
                        FakerFactory.fakerInt(),
                        FakerFactory.fakerString(),
                    ),
                    GenreResponse(
                        FakerFactory.fakerInt(),
                        FakerFactory.fakerString(),
                    )
                )
            )

        fun makeGameResponseList(count: Int): List<GameResponse> {
            val gameResponseList = mutableListOf<GameResponse>()
            repeat(count) {
                gameResponseList.add(makeGameResponse())
            }
            return gameResponseList
        }
    }
}