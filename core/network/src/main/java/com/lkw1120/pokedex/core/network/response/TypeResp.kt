package com.lkw1120.pokedex.core.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TypeResp(
    @field:Json(name = "slot")
    val slot: Long,
    @field:Json(name = "type")
    val type: ResultResp,
)