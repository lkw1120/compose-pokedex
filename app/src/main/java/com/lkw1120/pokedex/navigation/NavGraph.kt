package com.lkw1120.pokedex.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lkw1120.pokedex.feature.detail.DetailScreen
import com.lkw1120.pokedex.feature.main.MainScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        modifier = Modifier.padding(0.dp),
        navController = navController,
        startDestination = startDestination,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }
    ) {
        composable(
            route = NavScreen.MainScreen.route
        ) {
            MainScreen { name, index ->
                navController.navigate(NavScreen.DetailScreen.route + "/$name/$index") {
                    launchSingleTop = true
                    popUpTo(NavScreen.MainScreen.route)
                }
            }
        }
        composable(
            route = NavScreen.DetailScreen.route + "/{name}/{index}",
        ) {
            DetailScreen(
                name = it.arguments?.getString("name")?:"bulbasaur",
                index = it.arguments?.getString("index")?.toLong()?:1
            ) {
                navController.navigateUp()
            }
        }
    }
}