package com.lkw1120.pokedex.core.database

import com.lkw1120.pokedex.core.database.entity.DetailEntity
import com.lkw1120.pokedex.core.database.entity.PokeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface LocalDataSource {

    fun getPokeList(): Flow<List<PokeEntity>>

    fun updatePokeData(entity: PokeEntity)

    fun deletePokeData(entity: PokeEntity)

    fun getDetailData(name: String): Flow<DetailEntity?>

    fun updateDetailData(entity: DetailEntity)

    fun deleteDetailData(entity: DetailEntity)

}

class LocalDataSourceImpl @Inject constructor (
    private val appDatabase: AppDatabase
): LocalDataSource {

    override fun getPokeList(): Flow<List<PokeEntity>> {
        return appDatabase.pokeDao().getPokeList()
    }

    override fun updatePokeData(entity: PokeEntity) {
        appDatabase.pokeDao().updatePokeData(entity)
    }

    override fun deletePokeData(entity: PokeEntity) {
        appDatabase.pokeDao().deletePokeData(entity)
    }

    override fun getDetailData(name: String): Flow<DetailEntity?> {
        return appDatabase.DetailDao().getDetailData(name)
    }

    override fun updateDetailData(entity: DetailEntity) {
        appDatabase.DetailDao().updateDetailData(entity)
    }

    override fun deleteDetailData(entity: DetailEntity) {
        appDatabase.DetailDao().deleteDetailData(entity)
    }
}