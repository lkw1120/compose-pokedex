package com.lkw1120.pokedex.di

import android.content.Context
import com.lkw1120.pokedex.datasource.LocalDataSource
import com.lkw1120.pokedex.datasource.LocalDataSourceImpl
import com.lkw1120.pokedex.datasource.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase.getDatabase(context)

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