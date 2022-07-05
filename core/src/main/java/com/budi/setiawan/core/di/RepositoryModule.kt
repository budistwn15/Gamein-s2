package com.budi.setiawan.core.di

import com.budi.setiawan.core.data.GameRepository
import com.budi.setiawan.core.domain.repository.IGameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(gameRepository: GameRepository): IGameRepository
}