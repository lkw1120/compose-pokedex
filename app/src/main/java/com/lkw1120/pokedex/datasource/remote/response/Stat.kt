package com.lkw1120.pokedex.datasource.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Stat(
    @field:Json(name = "base_stat")
    val baseStat: Long,
    @field:Json(name = "effort")
    val effort: Long,
    @field:Json(name = "stat")
    val stat: Result,
)