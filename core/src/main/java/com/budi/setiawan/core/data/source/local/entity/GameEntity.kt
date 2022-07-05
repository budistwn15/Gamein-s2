package com.budi.setiawan.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_games")
data class GameEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "released")
    var released: String,

    @ColumnInfo(name = "background_image")
    var background_image: String,

    @ColumnInfo(name = "rating")
    var rating: Float,

    @ColumnInfo(name = "ratings_count")
    val ratings_count: Int,

    @ColumnInfo(name = "reviews_count")
    val reviews_count: Int,

    @ColumnInfo(name = "added")
    val added: Int,

    @ColumnInfo(name = "description")
    val description:  String?,

    @ColumnInfo(name = "website")
    val website: String?,

    @ColumnInfo(name = "genres")
    var genres: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)