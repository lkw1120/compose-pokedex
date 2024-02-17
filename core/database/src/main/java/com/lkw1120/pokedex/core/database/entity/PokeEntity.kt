package com.lkw1120.pokedex.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poke_table")
data class PokeEntity(
    @PrimaryKey
    val id: Long,
    val name: String,
    val url: String,
)
