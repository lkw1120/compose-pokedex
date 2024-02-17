package com.lkw1120.pokedex.core.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lkw1120.pokedex.core.common.Constants.PAGE_SIZE
import com.lkw1120.pokedex.core.data.mapper.toPokeItemFromRemote
import com.lkw1120.pokedex.core.model.PokeItem
import com.lkw1120.pokedex.core.repository.NetworkRepository
import javax.inject.Inject

class PokedexPagingSource @Inject constructor(
    private val networkRepository: NetworkRepository,
) : PagingSource<Int, PokeItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokeItem> {
        return try {
            val page = params.key ?: 0
            val response = networkRepository.getPokeList(page * PAGE_SIZE)

            LoadResult.Page(
                data = response.resultResps.map { toPokeItemFromRemote(it) },
                prevKey = if(page == 0) null else page.minus(1),
                nextKey = page.plus(1),
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokeItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}