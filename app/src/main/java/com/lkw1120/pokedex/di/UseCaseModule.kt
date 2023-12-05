package com.lkw1120.pokedex.di

import com.lkw1120.pokedex.repository.DatabaseRepository
import com.lkw1120.pokedex.repository.NetworkRepository
import com.lkw1120.pokedex.usecase.PokedexUseCase
import com.lkw1120.pokedex.usecase.PokedexUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun providePokedexUseCase(
        databaseRepository: DatabaseRepository,
        networkRepository: NetworkRepository
    ): PokedexUseCase = PokedexUseCaseImpl(
        databaseRepository = databaseRepository,
        networkRepository = networkRepository
    )
}