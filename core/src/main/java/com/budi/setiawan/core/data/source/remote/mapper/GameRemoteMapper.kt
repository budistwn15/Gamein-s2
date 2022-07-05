package com.budi.setiawan.core.data.source.remote.mapper

import com.budi.setiawan.core.data.source.local.entity.GameEntity
import com.budi.setiawan.core.data.source.remote.response.GameResponse
import com.budi.setiawan.core.data.source.remote.response.GenreResponse
import javax.inject.Inject

open class GameRemoteMapper @Inject constructor() : EntityMapper<GameResponse, GameEntity> {
    override fun mapRemoteToEntity(remote: GameResponse): GameEntity {
        val genres = getGenresName(remote.genres)
        return GameEntity(
            id = remote.id,
            name = remote.name,
            released = remote.released,
            background_image = remote.background_image,
            rating = remote.rating,
            ratings_count = remote.ratings_count,
            reviews_count = remote.reviews_count,
            added = remote.added,
            description = remote.description,
            website = remote.website,
            genres = genres,
            isFavorite = false
        )
    }

    override fun mapRemoteToEntities(remotes: List<GameResponse>): List<GameEntity> {
        val list = mutableListOf<GameEntity>()

        remotes.map {
            val genres = getGenresName(it.genres)
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
                genres = genres,
                isFavorite = false
            )
            list.add(game)
        }

        return list
    }

    private fun getGenresName(data: List<GenreResponse>?): String {
        val result = StringBuilder().append("")

        data?.let {
            for (i in it.indices)
                if (i < it.size - 1) result.append("${it[i].name}, ")
                else result.append(it[i].name)
        }
        return result.toString()
    }
}
