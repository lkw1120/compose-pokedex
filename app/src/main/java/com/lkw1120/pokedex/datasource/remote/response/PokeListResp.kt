package com.lkw1120.pokedex.datasource.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeListResp(
    @field:Json(name = "count")
    val count: Long,
    @field:Json(name = "next")
    val next: String?,
    @field:Json(name = "previous")
    val previous: String?,
    @field:Json(name = "results")
    val results: List<Result>
)