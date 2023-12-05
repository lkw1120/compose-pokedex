package com.lkw1120.pokedex.datasource.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Type(
    @field:Json(name = "slot")
    val slot: Long,
    @field:Json(name = "type")
    val type: Result,
)