package com.lkw1120.pokedex.core.network.di

import android.app.Application
import com.lkw1120.pokedex.core.network.ApiConnection
import com.lkw1120.pokedex.core.network.ApiService
import com.lkw1120.pokedex.core.network.RemoteDataSource
import com.lkw1120.pokedex.core.network.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(
        application: Application,
    ): ApiService = ApiConnection.getService()

    @Singleton
    @Provides
    fun provideRemoteDataSource(
        apiService: ApiService
    ): RemoteDataSource {
        return RemoteDataSourceImpl(
            apiService = apiService
        )
    }

}