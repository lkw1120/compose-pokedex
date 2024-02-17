package com.lkw1120.pokedex.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.lkw1120.pokedex.core.database.entity.PokeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokeDao {

    @Query("SELECT * FROM poke_table ORDER BY id ASC")
    fun getPokeList(
    ): Flow<List<PokeEntity>>

    @Upsert
    fun updatePokeData(
        entity: PokeEntity
    )

    @Delete
    fun deletePokeData(
        entity: PokeEntity
    )
}