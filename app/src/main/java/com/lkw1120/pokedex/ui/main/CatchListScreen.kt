package com.lkw1120.pokedex.ui.main

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.lkw1120.pokedex.ui.component.ErrorScreen
import com.lkw1120.pokedex.ui.component.ListEmptyScreen
import com.lkw1120.pokedex.ui.component.LoadingScreen
import com.lkw1120.pokedex.ui.component.PokemonItem

@Composable
fun CatchListScreen (
    modifier: Modifier = Modifier,
    viewModel: CatchListViewModel,
    onNavigate: (name: String, index: Long) -> Unit
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current


    val itemSize = when(configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            configuration.screenWidthDp.dp * 0.25f - 18.dp
        }
        else -> {
            configuration.screenWidthDp.dp * 0.5f - 18.dp
        }
    }

    val pokeList by viewModel.pokeList.collectAsState()

    LaunchedEffect(pokeList) {
        viewModel.success()
    }

    val currentState by viewModel.catchListScreenState.collectAsState()

    when(currentState) {
        is CatchListScreenState.Loading -> {
            LoadingScreen()
        }
        is CatchListScreenState.Error -> {
            val message =
                (currentState as CatchListScreenState.Error).errorMessage?:""
            ErrorScreen(message = message)
        }
        is CatchListScreenState.Success -> {
            if(pokeList.isEmpty()) {
                ListEmptyScreen()
            }
            else {
                LazyVerticalGrid(
                    modifier = Modifier.fillMaxSize(),
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(pokeList.size) { index ->
                        pokeList[index].let { item ->
                            PokemonItem(
                                item = item,
                                id = item.id,
                                size = itemSize,
                                onClick = {
                                    onNavigate(item.name, item.id)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
