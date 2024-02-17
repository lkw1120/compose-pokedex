package com.lkw1120.pokedex.core.repository.di

import com.lkw1120.pokedex.core.database.LocalDataSource
import com.lkw1120.pokedex.core.network.RemoteDataSource
import com.lkw1120.pokedex.core.repository.DatabaseRepository
import com.lkw1120.pokedex.core.repository.DatabaseRepositoryImpl
import com.lkw1120.pokedex.core.repository.NetworkRepository
import com.lkw1120.pokedex.core.repository.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideDatabaseRepository(
        localDataSource: LocalDataSource
    ): DatabaseRepository = DatabaseRepositoryImpl(localDataSource)

    @Singleton
    @Provides
    fun provideNetworkRepository(
        remoteDataSource: RemoteDataSource
    ): NetworkRepository = NetworkRepositoryImpl(remoteDataSource)

}