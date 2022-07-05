package com.budi.setiawan.gamein.di

import com.budi.setiawan.core.domain.usecase.GameUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun gameUseCase(): GameUseCase
}