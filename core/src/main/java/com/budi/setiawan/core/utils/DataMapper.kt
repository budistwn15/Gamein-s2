package com.budi.setiawan.core.utils

import com.budi.setiawan.core.data.source.local.entity.GameEntity
import com.budi.setiawan.core.data.source.remote.response.GameResponse
import com.budi.setiawan.core.data.source.remote.response.GenreResponse
import com.budi.setiawan.core.domain.model.Game

object DataMapper {
    fun mapResponsesToEntities(input: List<GameResponse>): List<GameEntity>{
        val games = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                id = it.id,
                name = it.name,
                released = it.released,
                background_image = it.background_image,
                rating = it.rating,
                ratings_count = it.ratings_count,
                reviews_count = it.reviews_count,
                added = it.added,
                description = it.description ?: "",
                website = it.website ?: "",
                genres = it.genres.joinToString(prefix = "", postfix = "") { genre ->
                    genre.name
                },
                isFavorite = false
            )
            games.add(game)
        }
        return games
    }

    fun mapFromEntity(input: GameEntity): Game {
        return Game(
            id = input.id,
            name = input.name,
            released = input.released,
            background_image = input.background_image,
            rating = input.rating,
            ratings_count = input.ratings_count,
            reviews_count = input.reviews_count,
            added = input.added,
            genres = input.genres,
            description = input.description ?: "",
            website = input.website ?: "",
            isFavorite = input.isFavorite
        )
    }

    fun mapResponsesToEntity(input: GameResponse): GameEntity {
        val genres = getGenresName(input.genres)
        return GameEntity(
            id = input.id,
            name = input.name,
            released = input.released,
            background_image = input.background_image,
            rating = input.rating,
            ratings_count = input.ratings_count,
            reviews_count = input.reviews_count,
            added = input.added,
            description = input.description,
            website = input.website,
            genres = genres,
            isFavorite = false
        )
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                id = it.id,
                name = it.name,
                released = it.released,
                background_image = it.background_image,
                rating = it.rating,
                ratings_count = it.ratings_count,
                reviews_count = it.reviews_count,
                added = it.added,
                description = it.description ?: "",
                website = it.description ?: "",
                genres = it.genres,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Game) = GameEntity(
        id = input.id,
        name = input.name,
        released = input.released,
        background_image = input.background_image,
        rating = input.rating,
        ratings_count = input.ratings_count,
        reviews_count = input.reviews_count,
        added = input.added,
        description = input.description ?: "",
        website = input.website ?: "",
        genres = input.genres,
        isFavorite = input.isFavorite,
    )

    private fun getGenresName(input: List<GenreResponse>?): String{
        val results = StringBuilder().append("")
        input?.let {
            for (i in it.indices)
                if (i < it.size - 1) results.append("${it[i].name}, ")
                else results.append(it[i].name)

        }
        return results.toString()
    }
}