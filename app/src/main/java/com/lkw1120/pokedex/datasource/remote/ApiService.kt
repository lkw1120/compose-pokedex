package com.lkw1120.pokedex.datasource.remote

import com.lkw1120.pokedex.common.Constants.PAGE_SIZE
import com.lkw1120.pokedex.datasource.remote.response.PokeDetailResp
import com.lkw1120.pokedex.datasource.remote.response.PokeListResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/api/v2/pokemon")
    suspend fun getPokeList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = PAGE_SIZE
    ): Response<PokeListResp>

    @GET("/api/v2/pokemon/{name}")
    suspend fun getPokeDetail(
        @Path("name") name: String
    ): Response<PokeDetailResp>

}