package com.lkw1120.pokedex.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.palette.graphics.Palette
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.lkw1120.pokedex.R
import com.lkw1120.pokedex.common.Constants.KILOGRAM_FORMAT
import com.lkw1120.pokedex.common.Constants.METER_FORMAT
import com.lkw1120.pokedex.common.Constants.PROFILE_URL
import com.lkw1120.pokedex.ui.component.ErrorScreen
import com.lkw1120.pokedex.ui.component.LoadingScreen
import com.lkw1120.pokedex.ui.component.StatItem
import com.lkw1120.pokedex.ui.component.TypeItem
import com.lkw1120.pokedex.usecase.model.PokeDetail

@Composable
fun PokeDetailScreen (
    modifier: Modifier = Modifier,
    index: Long,
    name: String,
    viewModel: PokeDetailViewModel = hiltViewModel(key = name),
    onNavigate: () -> Unit
) {

    SideEffect {
        viewModel.getPokeDetail(name)
    }

    val currentState by viewModel.pokeDetailScreenState.collectAsState()

    when(currentState) {
        is PokeDetailScreenState.Loading -> {
            LoadingScreen()
        }

        is PokeDetailScreenState.Error -> {
            val status =
                (currentState as PokeDetailScreenState.Error)
            val message = status.errorMessage ?: ""
            ErrorScreen(message = message)
        }

        is PokeDetailScreenState.Success -> {
            val status =
                (currentState as PokeDetailScreenState.Success)
            val pokeDetail = status.pokeDetail
            val isSaved = status.isSaved

            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .background(colorResource(id = R.color.gray))
                    .verticalScroll(scrollState),
            ) {
                PokeImageScreen(
                    pokeDetail = pokeDetail,
                    isSaved = isSaved,
                    viewModel = viewModel,
                    onNavigate = { onNavigate() }
                )
                PokeStatsScreen(
                    pokeDetail = pokeDetail,
                    viewModel = viewModel,
                    onNavigate = { onNavigate() }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun PokeImageScreen (
    modifier: Modifier = Modifier,
    pokeDetail: PokeDetail,
    isSaved: Boolean,
    viewModel: PokeDetailViewModel,
    onNavigate:() -> Unit,
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current

    val defaultColor =
        MaterialTheme.colorScheme.secondary.value.toInt()
    val backgroundColor =
        remember { mutableStateOf(Color(defaultColor)) }
    val tintColor =
        remember { mutableStateOf(Color(context.getColor(R.color.white))) }
    val source =
        String.format(PROFILE_URL, pokeDetail.id)

    val size = configuration.screenWidthDp.dp * 0.5f

    val systemUiController = rememberSystemUiController()
    LaunchedEffect(backgroundColor.value) {
        systemUiController.setStatusBarColor(backgroundColor.value)
        if(systemUiController.statusBarDarkContentEnabled) {
            tintColor.value = Color(context.getColor(R.color.gray))
        }
        else {
            tintColor.value = Color(context.getColor(R.color.white))
        }
    }

    Card(
        modifier = Modifier,
        shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp)
    ) {
        Box(
            modifier = Modifier
                .height(270.dp)
                .background(backgroundColor.value)
        ) {
            CenterAlignedTopAppBar(
                title = { },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(6.dp, 12.dp)
                            .size(36.dp)
                            .clickable { onNavigate() },
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = tintColor.value
                    )
                },
                actions = {
                    Icon(
                        modifier = Modifier
                            .padding(6.dp, 12.dp)
                            .size(36.dp)
                            .clickable {
                                if (isSaved) {
                                    viewModel.deletePokeDetail(pokeDetail)
                                } else {
                                    viewModel.updatePokeDetail(pokeDetail)
                                }
                            },
                        imageVector = if(isSaved) {
                            Icons.Default.Favorite
                        }
                        else {
                            Icons.Default.FavoriteBorder
                        },
                        contentDescription = null,
                        tint = tintColor.value
                    )

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.transparent)
                )
            )
            GlideImage(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .width(size)
                    .height(size),
                model = source,
                contentDescription = null,
            ) { requestBuilder ->
                requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(false)
                    .centerCrop()
                    .listener(object : RequestListener<Drawable> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable,
                            model: Any,
                            target: Target<Drawable>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            val bitmap =
                                resource.toBitmap().copy(Bitmap.Config.ARGB_8888, true)
                            Palette.Builder(bitmap).generate { palette ->
                                backgroundColor.value = Color(
                                    palette?.getDominantColor(defaultColor) ?: defaultColor
                                )
                            }
                            return false
                        }
                    })
            }
        }
    }
}

@Composable
fun PokeStatsScreen(
    modifier: Modifier = Modifier,
    pokeDetail: PokeDetail,
    viewModel: PokeDetailViewModel,
    onNavigate: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(24.dp)
        )
        Text(
            modifier = Modifier,
            text = pokeDetail.name,
            style = TextStyle(
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.white)
            )
        )
        Spacer(
            modifier = Modifier
                .height(12.dp)
        )
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            pokeDetail.types.forEach { item ->
                TypeItem(
                    item = item
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(12.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.stat_height),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white)
                    )
                )
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )
                Text(
                    modifier = Modifier,
                    text = String.format(METER_FORMAT, pokeDetail.height * 0.1f),
                    style = TextStyle(
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white)
                    )
                )
            }
            Column(
                modifier = Modifier
                    .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier,
                    text = stringResource(id = R.string.stat_weight),
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white)
                    )
                )
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                )
                Text(
                    modifier = Modifier,
                    text = String.format(KILOGRAM_FORMAT, pokeDetail.weight * 0.1f),
                    style = TextStyle(
                        fontSize = 21.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.white)
                    )
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(12.dp)
        )
        Text(
            modifier = Modifier,
            text = stringResource(id = R.string.stat_base_stat),
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.white)
            )
        )
        Spacer(
            modifier = Modifier
                .height(12.dp)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            pokeDetail.stats.forEach { item ->
                StatItem(
                    item = item
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(24.dp)
        )
    }
}