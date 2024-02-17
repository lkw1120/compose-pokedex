package com.lkw1120.pokedex.core.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokeDetailResp(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "base_experience")
    val baseExperience: Long,
    @field:Json(name = "weight")
    val weight: Long,
    @field:Json(name = "height")
    val height: Long,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "order")
    val order: Long,
    @field:Json(name = "stats")
    val statResps: List<StatResp>,
    @field:Json(name = "types")
    val typeResps: List<TypeResp>,
)
