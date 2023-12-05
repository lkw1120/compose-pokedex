package com.lkw1120.pokedex.datasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lkw1120.pokedex.usecase.model.StatInfo
import com.lkw1120.pokedex.usecase.model.TypeInfo

@Entity(tableName = "detail_table")
data class DetailEntity(
    @PrimaryKey
    val id: Long,
    val baseExperience: Long,
    val weight: Long,
    val height: Long,
    val name: String,
    val order: Long,
    val stats: List<StatInfo>,
    val types: List<TypeInfo>,
) {

}