package com.budi.setiawan.core.di

import com.budi.setiawan.core.BuildConfig
import com.budi.setiawan.core.data.source.remote.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, sslOne)
            .add(hostname, sslTwo)
            .add(hostname, sslThree)
            .add(hostname, sslFour)
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    fun provideApiService(client: OkHttpClient): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    companion object{
        private const val hostname = BuildConfig.HOSTNAME
        private const val sslOne = "sha256/Vt5/77IBRU8Ic76wffoVpn2hrTRotDK+cuASoGoEzS0="
        private const val sslTwo = "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA="
        private const val sslThree = "sha256/6i+nf58l8neEnNarZOvxiYfVbt2S2xurswGQQBBMa0U="
        private const val sslFour = "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4="
    }
}