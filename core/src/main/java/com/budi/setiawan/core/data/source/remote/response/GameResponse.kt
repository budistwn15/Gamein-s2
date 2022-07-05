package com.budi.setiawan.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @field: SerializedName("id")
    val id: Int,

    @field: SerializedName("name")
    val name: String,

    @field: SerializedName("released")
    val released: String,

    @field: SerializedName("background_image")
    val background_image: String,

    @field: SerializedName("rating")
    val rating: Float,

    @field: SerializedName("ratings_count")
    val ratings_count: Int,

    @field: SerializedName("reviews_count")
    val reviews_count: Int,

    @field: SerializedName("added")
    val added: Int,

    @field: SerializedName("description")
    val description: String?,

    @field: SerializedName("website")
    val website: String?,

    @field: SerializedName("genres")
    val genres: List<GenreResponse>,
)