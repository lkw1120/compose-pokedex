package com.lkw1120.pokedex.core.repository

import com.lkw1120.pokedex.core.database.LocalDataSource
import com.lkw1120.pokedex.core.database.entity.DetailEntity
import com.lkw1120.pokedex.core.database.entity.PokeEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DatabaseRepository {

    fun getPokeList(): Flow<List<PokeEntity>>

    fun updatePokeData(entity: PokeEntity)

    fun deletePokeData(entity: PokeEntity)

    fun getDetailData(name: String): Flow<DetailEntity?>

    fun updateDetailData(entity: DetailEntity)

    fun deleteDetailData(entity: DetailEntity)

}

class DatabaseRepositoryImpl @Inject constructor (
    private val localDataSource: LocalDataSource
): DatabaseRepository {

    override fun getPokeList(): Flow<List<PokeEntity>> {
        return localDataSource.getPokeList()
    }

    override fun updatePokeData(entity: PokeEntity) {
        localDataSource.updatePokeData(entity)
    }

    override fun deletePokeData(entity: PokeEntity) {
        localDataSource.deletePokeData(entity)
    }

    override fun getDetailData(name: String): Flow<DetailEntity?> {
        return localDataSource.getDetailData(name)
    }

    override fun updateDetailData(entity: DetailEntity) {
        localDataSource.updateDetailData(entity)
    }

    override fun deleteDetailData(entity: DetailEntity) {
        localDataSource.deleteDetailData(entity)
    }
}