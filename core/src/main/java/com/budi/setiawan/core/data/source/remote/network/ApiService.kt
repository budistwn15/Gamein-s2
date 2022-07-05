package com.budi.setiawan.core.data.source.remote.network

import com.budi.setiawan.core.BuildConfig
import com.budi.setiawan.core.data.source.remote.response.GameResponse
import com.budi.setiawan.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") key: String = API_KEY
    ) : ListGameResponse

    @GET("games/{id}")
    suspend fun getGamesDetail(
        @Path("id") id: Int,
        @Query("key") key: String = API_KEY
    ) : GameResponse

    companion object{
        private const val API_KEY = BuildConfig.API_KEY
    }
}