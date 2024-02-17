package com.lkw1120.pokedex.feature.main.pager

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String,
    val page: Int
) {
    data object PokeList : BottomNavItem(
        route = PagerRoute.PokeListScreen,
        icon = Icons.Default.List,
        label = "Pokemons",
        page = 0
    )
    data object CatchList : BottomNavItem(
        route = PagerRoute.CatchListScreen,
        icon = Icons.Default.Favorite,
        label = "Favorites",
        page = 1
    )
}