package com.budi.setiawan.core.di

import android.content.Context
import androidx.room.Room
import com.budi.setiawan.core.data.source.local.room.GameDao
import com.budi.setiawan.core.data.source.local.room.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): GameDatabase{
        val passphrase = SQLiteDatabase.getBytes("gamein".toCharArray())
        val factory = SupportFactory(passphrase)

        return Room.databaseBuilder(context, GameDatabase::class.java, "Game.db")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideGameDao(gameDatabase: GameDatabase): GameDao = gameDatabase.gameDao()
}