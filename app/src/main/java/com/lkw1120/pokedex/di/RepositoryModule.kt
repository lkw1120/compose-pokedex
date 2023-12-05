package com.lkw1120.pokedex.di

import com.lkw1120.pokedex.datasource.LocalDataSource
import com.lkw1120.pokedex.datasource.RemoteDataSource
import com.lkw1120.pokedex.repository.DatabaseRepository
import com.lkw1120.pokedex.repository.DatabaseRepositoryImpl
import com.lkw1120.pokedex.repository.NetworkRepository
import com.lkw1120.pokedex.repository.NetworkRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

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