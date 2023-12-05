package com.lkw1120.pokedex.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lkw1120.pokedex.R
import com.lkw1120.pokedex.ui.main.pager.BottomNavBar
import com.lkw1120.pokedex.ui.main.pager.BottomNavItem
import com.lkw1120.pokedex.ui.main.pager.PagerScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigate: (name: String, index: Long) -> Unit
) {
    val context = LocalContext.current

    val items = listOf(
        BottomNavItem.PokeList,
        BottomNavItem.CatchList
    )
    val pagerState = rememberPagerState()

    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color(context.getColor(R.color.red))
        )
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier,
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = colorResource(id = R.color.white),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 21.sp
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.red)
                )
            )
        },
        bottomBar = {
            BottomNavBar(
                pagerState = pagerState,
                items = items
            )
        }
    ) { innerPaddingValues ->
        PagerScreen(
            modifier = Modifier
                .padding(
                    top = innerPaddingValues.calculateTopPadding(),
                    bottom = innerPaddingValues.calculateBottomPadding()
                )
                .fillMaxSize()
                .background(colorResource(id = R.color.gray)),
            pagerState = pagerState,
            onNavigate = onNavigate
        )
    }
}