package com.budi.setiawan.core.data.source.remote

import android.util.Log
import com.budi.setiawan.core.data.source.remote.mapper.GameRemoteMapper
import com.budi.setiawan.core.data.source.remote.network.ApiResponse
import com.budi.setiawan.core.data.source.remote.network.ApiService
import com.budi.setiawan.core.data.source.remote.response.GameResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService, val mapper: GameRemoteMapper){
    suspend fun getAllGames(): Flow<ApiResponse<List<GameResponse>>>{
        return flow {
            try {
                val response = apiService.getGames()
                val dataArray = response.games
                if(dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.games))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getGamesDetail(id: Int) : Flow<ApiResponse<GameResponse>> {
        return flow {
            try {
                val response = apiService.getGamesDetail(id)
                if (response.description?.isNotEmpty() == true) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}