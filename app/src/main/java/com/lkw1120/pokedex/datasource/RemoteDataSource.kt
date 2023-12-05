package com.lkw1120.pokedex.datasource

import com.lkw1120.pokedex.datasource.remote.ApiService
import com.lkw1120.pokedex.datasource.remote.response.PokeDetailResp
import com.lkw1120.pokedex.datasource.remote.response.PokeListResp
import retrofit2.Response
import javax.inject.Inject

interface RemoteDataSource {

    suspend fun getPokeList(offset: Int): Response<PokeListResp>

    suspend fun getPokeDetail(name: String): Response<PokeDetailResp>
}

class RemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : RemoteDataSource {

    override suspend fun getPokeList(offset: Int): Response<PokeListResp> {
        return apiService.getPokeList(offset = offset)
    }


    override suspend fun getPokeDetail(name: String): Response<PokeDetailResp> {
        return apiService.getPokeDetail(name)
    }

}