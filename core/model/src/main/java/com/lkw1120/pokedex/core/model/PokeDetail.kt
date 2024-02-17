package com.lkw1120.pokedex.core.model

data class PokeDetail(
    val id: Long,
    val baseExperience: Long,
    val weight: Long,
    val height: Long,
    val name: String,
    val order: Long,
    val stats: List<StatInfo>,
    val types: List<TypeInfo>,
)
