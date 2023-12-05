package com.lkw1120.pokedex.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lkw1120.pokedex.R
import com.lkw1120.pokedex.ui.detail.PokeDetailScreen

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    name: String,
    index: Long,
    onNavigate: () -> Unit
) {
    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(context.getColor(R.color.gray))
        )
    }

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = colorResource(id = R.color.gray)
    ) {
        PokeDetailScreen(
            index = index,
            name = name,
            onNavigate = { onNavigate() }
        )
    }
}