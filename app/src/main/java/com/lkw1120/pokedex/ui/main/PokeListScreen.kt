package com.lkw1120.pokedex.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.lkw1120.pokedex.ui.component.ErrorScreen
import com.lkw1120.pokedex.ui.component.LoadingScreen
import com.lkw1120.pokedex.ui.component.PokemonItem

@Composable
fun PokeListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokeListViewModel,
    onNavigate: (name: String, index: Long) -> Unit
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val pagingList = viewModel.pokeList.collectAsLazyPagingItems()

    val itemSize = when(configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            configuration.screenWidthDp.dp * 0.25f - 18.dp
        }
        else -> {
            configuration.screenWidthDp.dp * 0.5f - 18.dp
        }
    }

    when(pagingList.loadState.refresh) {
        is LoadState.Loading -> {
            viewModel.refresh()
        }
        is LoadState.Error -> {
            viewModel.error("loading error")
        }
        else -> {
            viewModel.success()
        }
    }

    val currentState by viewModel.pokeListScreenState.collectAsState()

    when(currentState) {
        is PokeListScreenState.Loading -> {
            LoadingScreen()
        }
        is PokeListScreenState.Error -> {
            val message =
                (currentState as PokeListScreenState.Error).errorMessage?:""
            ErrorScreen(message = message)
        }
        is PokeListScreenState.Success -> {
            LazyVerticalGrid(
                modifier = Modifier.fillMaxSize(),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items(pagingList.itemCount) { index ->
                    pagingList[index]?.let { item ->
                        PokemonItem(
                            item = item,
                            id = item.id,
                            size = itemSize,
                            onClick = {
                                onNavigate(item.name,item.id)
                            }
                        )
                    }
                }
            }
        }
    }
}

