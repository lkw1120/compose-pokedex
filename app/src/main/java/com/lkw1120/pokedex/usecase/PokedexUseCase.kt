package com.lkw1120.pokedex.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.lkw1120.pokedex.common.Constants.PAGE_SIZE
import com.lkw1120.pokedex.repository.DatabaseRepository
import com.lkw1120.pokedex.repository.NetworkRepository
import com.lkw1120.pokedex.usecase.mapper.toDetailEntityFromPokeDetail
import com.lkw1120.pokedex.usecase.mapper.toPokeDetailFromLocal
import com.lkw1120.pokedex.usecase.mapper.toPokeDetailFromRemote
import com.lkw1120.pokedex.usecase.mapper.toPokeEntityFromPokeDetail
import com.lkw1120.pokedex.usecase.mapper.toPokeItemFromLocal
import com.lkw1120.pokedex.usecase.model.PokeDetail
import com.lkw1120.pokedex.usecase.model.PokeItem
import com.lkw1120.pokedex.usecase.paging.PokedexPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface PokedexUseCase {

    fun getPokeListFromRemote(): Flow<PagingData<PokeItem>>

    fun getPokeListFromLocal(): Flow<List<PokeItem>>

    fun getPokeDetailFromRemote(name: String): Flow<PokeDetail>

    fun getPokeDetailFromLocal(name: String): Flow<PokeDetail?>

    suspend fun updatePokeDetail(data: PokeDetail)

    suspend fun deletePokeDetail(data: PokeDetail)
}

class PokedexUseCaseImpl @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val networkRepository: NetworkRepository
): PokedexUseCase {

    override fun getPokeListFromRemote(): Flow<PagingData<PokeItem>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
            ),
            pagingSourceFactory = { PokedexPagingSource(networkRepository) }
        ).flow
    }

    override fun getPokeListFromLocal(): Flow<List<PokeItem>> {
        return databaseRepository.getPokeList().map { list ->
            list.map { item ->
                toPokeItemFromLocal(item)
            }
        }
    }

    override fun getPokeDetailFromRemote(name: String): Flow<PokeDetail> {
        return flow {
            val response = networkRepository
                .getPokeDetail(name)
            val pokeDetail = toPokeDetailFromRemote(response)
            emit(pokeDetail)
        }
    }

    override fun getPokeDetailFromLocal(name: String): Flow<PokeDetail?> {
        return databaseRepository
                .getDetailData(name)
                .map {
                    toPokeDetailFromLocal(it)
                }
    }

    override suspend fun updatePokeDetail(data: PokeDetail) {
        databaseRepository.updateDetailData(toDetailEntityFromPokeDetail(data))
        databaseRepository.updatePokeData(toPokeEntityFromPokeDetail(data))
    }

    override suspend fun deletePokeDetail(data: PokeDetail) {
        databaseRepository.deleteDetailData(toDetailEntityFromPokeDetail(data))
        databaseRepository.deletePokeData(toPokeEntityFromPokeDetail(data))
    }
}