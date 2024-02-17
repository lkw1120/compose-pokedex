package com.lkw1120.pokedex.feature.main.pager

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lkw1120.pokedex.core.common.R
import com.lkw1120.pokedex.feature.main.CatchListScreen
import com.lkw1120.pokedex.feature.main.CatchListViewModel
import com.lkw1120.pokedex.feature.main.PokeListScreen
import com.lkw1120.pokedex.feature.main.PokeListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerScreen(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    pokeListViewModel: PokeListViewModel = hiltViewModel(),
    catchListViewModel: CatchListViewModel = hiltViewModel(),
    onNavigate: (name: String, index: Long) -> Unit
) {

    Box(
        modifier = modifier
    ) {
        HorizontalPager(
            modifier = Modifier
                .fillMaxSize(),
            state = pagerState
        ) { page ->
            when (page) {
                0 -> PokeListScreen(
                    viewModel = pokeListViewModel,
                    onNavigate = onNavigate
                )
                1 -> CatchListScreen(
                    viewModel = catchListViewModel,
                    onNavigate = onNavigate
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    items: List<BottomNavItem>
) {
    Row(
        modifier = Modifier
            .height(65.dp)
            .fillMaxWidth()
            .background(colorResource(id = R.color.white)),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items.forEach { item ->
            AddItem(
                item = item,
                pagerState = pagerState
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddItem(
    modifier: Modifier = Modifier,
    item: BottomNavItem,
    pagerState: PagerState
) {
    val coroutineScope = rememberCoroutineScope()
    val selected = pagerState.currentPage == item.page
    val tintColor = if (selected) {
        colorResource(id = R.color.red)
    }
    else {
        colorResource(id = R.color.gray)
    }

    Box(
        modifier = Modifier
            .wrapContentSize()
            .clickable(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(item.page)
                    }
                }
            )
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                imageVector = item.icon,
                contentDescription = null,
                tint = tintColor
            )
            Text(
                text = item.label,
                style = TextStyle(
                    color = tintColor,
                    fontSize = 15.sp
                )
            )
        }
    }
}