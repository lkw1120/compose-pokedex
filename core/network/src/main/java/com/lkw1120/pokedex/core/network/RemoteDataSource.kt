package com.lkw1120.pokedex.core.network

import com.lkw1120.pokedex.core.network.response.PokeDetailResp
import com.lkw1120.pokedex.core.network.response.PokeListResp
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getPokeList(offset: Int): PokeListResp

    suspend fun getPokeDetail(name: String): PokeDetailResp
}

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getPokeList(offset: Int): PokeListResp {
        val response = apiService.getPokeList(offset)
        if(response.isSuccessful) {
            return response.body()?:throw Exception()
        }
        else throw Exception(response.errorBody().toString())
    }


    override suspend fun getPokeDetail(name: String): PokeDetailResp {
        val response = apiService.getPokeDetail(name)
        if(response.isSuccessful) {
            return response.body()?:throw Exception()
        }
        else throw Exception(response.errorBody().toString())
    }

}