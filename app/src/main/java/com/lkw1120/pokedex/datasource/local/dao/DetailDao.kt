package com.lkw1120.pokedex.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.lkw1120.pokedex.datasource.entity.DetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DetailDao {

    @Query("SELECT * FROM detail_table WHERE :name = name")
    fun getDetailData(
        name: String
    ): Flow<DetailEntity?>

    @Upsert
    fun updateDetailData(
        entity: DetailEntity
    )

    @Delete
    fun deleteDetailData(
        entity: DetailEntity
    )
}