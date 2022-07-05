package com.budi.setiawan.favorite.factory

import com.budi.setiawan.core.domain.model.Game

class GameFactory {
    companion object Factory {
        private fun makeGame(): Game =
            Game(
                DataFactory.randomInt(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomDouble(),
                DataFactory.randomInt(),
                DataFactory.randomInt(),
                DataFactory.randomInt(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomString(),
                DataFactory.randomBoolean()
            )

        fun makeGameList(count: Int): List<Game> {
            val gameList = mutableListOf<Game>()
            repeat(count) {
                gameList.add(makeGame())
            }
            return gameList
        }
    }
}