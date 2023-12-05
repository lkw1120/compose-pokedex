package com.lkw1120.pokedex.datasource.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "url")
    val url: String,
)