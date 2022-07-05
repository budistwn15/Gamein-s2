package com.budi.setiawan.core.data.source.local.mapper

import com.budi.setiawan.core.data.source.local.entity.GameEntity
import com.budi.setiawan.core.domain.model.Game
import javax.inject.Inject

open class GameMapper @Inject constructor() : DataMapper<GameEntity, Game> {
    override fun mapFromEntity(entity: GameEntity): Game =
        Game(
            id = entity.id,
            name = entity.name,
            released = entity.released,
            background_image = entity.background_image,
            rating = entity.rating,
            ratings_count = entity.ratings_count,
            reviews_count = entity.reviews_count,
            added = entity.added,
            genres = entity.genres,
            description = entity.description ?: "",
            website = entity.website ?: "",
            isFavorite = entity.isFavorite
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
