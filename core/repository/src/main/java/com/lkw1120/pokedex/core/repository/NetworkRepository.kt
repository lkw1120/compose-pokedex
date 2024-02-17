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
        val response = remoteDataSource.getPokeList(offset)
        if(response.isSuccessful) {
            return response.body()?:throw Exception()
        }
        else throw Exception(response.errorBody().toString())
    }

    override suspend fun getPokeDetail(name: String): PokeDetailResp {
        val response = remoteDataSource.getPokeDetail(name)
        if(response.isSuccessful) {
            return response.body()?:throw Exception()
        }
        else throw Exception(response.errorBody().toString())
    }

}