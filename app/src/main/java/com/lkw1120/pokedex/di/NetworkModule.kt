package com.lkw1120.pokedex.di

import android.content.Context
import com.lkw1120.pokedex.datasource.RemoteDataSource
import com.lkw1120.pokedex.datasource.RemoteDataSourceImpl
import com.lkw1120.pokedex.datasource.remote.ApiConnection
import com.lkw1120.pokedex.datasource.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(
        @ApplicationContext context: Context
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