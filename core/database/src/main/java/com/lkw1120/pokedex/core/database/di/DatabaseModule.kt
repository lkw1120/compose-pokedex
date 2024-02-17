package com.lkw1120.pokedex.core.database.di

import android.app.Application
import com.lkw1120.pokedex.core.database.AppDatabase
import com.lkw1120.pokedex.core.database.LocalDataSource
import com.lkw1120.pokedex.core.database.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        application: Application,
    ): AppDatabase = AppDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun provideLocalDataSource(
        appDatabase: AppDatabase
    ): LocalDataSource {
        return LocalDataSourceImpl(
            appDatabase = appDatabase
        )
    }

}