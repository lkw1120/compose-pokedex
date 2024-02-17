package com.lkw1120.pokedex.navigation

sealed class NavScreen(val route: String) {
    data object MainScreen : NavScreen(NavRoute.mainScreen)
    data object DetailScreen : NavScreen(NavRoute.detailScreen)
}