package com.lkw1120.pokedex.core.data.di

import com.lkw1120.pokedex.core.data.PokedexUseCase
import com.lkw1120.pokedex.core.data.PokedexUseCaseImpl
import com.lkw1120.pokedex.core.repository.DatabaseRepository
import com.lkw1120.pokedex.core.repository.NetworkRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

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