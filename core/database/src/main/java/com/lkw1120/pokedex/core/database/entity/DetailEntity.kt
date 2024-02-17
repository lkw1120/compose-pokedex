package com.lkw1120.pokedex.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detail_table")
data class DetailEntity(
    @PrimaryKey
    val id: Long,
    val baseExperience: Long,
    val weight: Long,
    val height: Long,
    val name: String,
    val order: Long,
    val stats: List<StatEntity>,
    val types: List<TypeEntity>,
) {

}