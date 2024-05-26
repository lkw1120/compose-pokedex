package com.lkw1120.pokedex.core.repository

import com.lkw1120.pokedex.core.network.RemoteDataSource
import com.lkw1120.pokedex.core.network.response.PokeDetailResp
import com.lkw1120.pokedex.core.network.response.PokeListResp
import javax.inject.Inject

interface NetworkRepository {

    suspend fun getPokeList(offset: Int): PokeListResp

    suspend fun getPokeDetail(name: String): PokeDetailResp

}

class NetworkRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): NetworkRepository {

    override suspend fun getPokeList(offset: Int): PokeListResp {
        return remoteDataSource.getPokeList(offset)
    }

    override suspend fun getPokeDetail(name: String): PokeDetailResp {
        return remoteDataSource.getPokeDetail(name)
    }

}