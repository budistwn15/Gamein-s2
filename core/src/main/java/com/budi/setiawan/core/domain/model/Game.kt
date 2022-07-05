package com.budi.setiawan.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(
    val id: Int,
    val name: String,
    val released: String,
    val background_image: String,
    val rating: Double,
    val ratings_count: Int,
    val reviews_count: Int,
    val added: Int,
    val genres: String,
    val description: String?,
    val website: String?,
    val isFavorite: Boolean
): Parcelable