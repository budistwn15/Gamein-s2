package com.budi.setiawan.core.data.source.local.mapper

import com.budi.setiawan.core.data.source.local.entity.GameEntity
import com.budi.setiawan.core.domain.model.Game
import javax.inject.Inject

open class GameMapper @Inject constructor() : DataMapper<GameEntity, Game> {
    override fun mapFromEntity(input: GameEntity): Game =
        Game(
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

    override fun mapToEntity(model: Game): GameEntity =
        GameEntity(
            id = model.id,
            name = model.name,
            released = model.released,
            background_image = model.background_image,
            rating = model.rating,
            ratings_count = model.ratings_count,
            reviews_count = model.reviews_count,
            added = model.added,
            description = model.description,
            website = model.website,
            genres = model.genres,
            isFavorite = false
        )

    override fun mapFromEntities(entities: List<GameEntity>): List<Game> =
        entities.map {
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
}
