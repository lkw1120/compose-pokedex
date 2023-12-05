package com.lkw1120.pokedex.ui.component

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.lkw1120.pokedex.R
import com.lkw1120.pokedex.common.Constants.ID_FORMAT
import com.lkw1120.pokedex.common.Constants.PROFILE_URL
import com.lkw1120.pokedex.common.Constants.STAT_FORMAT
import com.lkw1120.pokedex.usecase.model.PokeItem
import com.lkw1120.pokedex.usecase.model.StatInfo
import com.lkw1120.pokedex.usecase.model.TypeInfo

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun PokemonItem(
    modifier: Modifier = Modifier,
    item: PokeItem,
    id: Long,
    size: Dp,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    val defaultColor = MaterialTheme.colorScheme.secondary.value.toInt()
    val backgroundColor = remember { mutableStateOf(Color(defaultColor)) }
    val source = String.format(PROFILE_URL, id)

    Card(
        modifier = Modifier
            .width(size)
            .height(size)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),

        ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor.value)
                .padding(12.dp),
        ) {
            Text(
                modifier = Modifier.align(Alignment.TopStart),
                text = String.format(ID_FORMAT,id),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            GlideImage(
                modifier = Modifier
                    .width(size * 0.65f)
                    .height(size * 0.65f)
                    .align(Alignment.Center),
                model = source,
                contentDescription = null,
            ) { requestBuilder ->
                requestBuilder
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .skipMemoryCache(false)
                    .centerCrop()
                    .listener(object: RequestListener<Drawable> {
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
                            val bitmap = resource.toBitmap().copy(Bitmap.Config.ARGB_8888, true)
                            Palette.Builder(bitmap).generate { palette ->
                                backgroundColor.value = Color(palette?.getDominantColor(defaultColor) ?: defaultColor)
                            }
                            return false
                        }
                    })
            }
            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = item.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
        }
    }
}

@Composable
fun TypeItem(
    modifier: Modifier = Modifier,
    item: TypeInfo
) {
    Card(
        modifier = Modifier
            .wrapContentSize(),
        colors = CardDefaults.cardColors(
            containerColor = getTypeColor(item.name)
        ),
        shape = RoundedCornerShape(36.dp),
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 36.dp, vertical = 3.dp)
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize(),
                text = item.name,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colorResource(id = R.color.white)
                )
            )
        }
    }
}

@Composable
fun StatItem(
    modifier: Modifier = Modifier,
    item: StatInfo
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(0.25f),
            text = getStatString(item.name),
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.white),
            )
        )
        Box(
            modifier = Modifier
                .weight(0.75f)
                .height(18.dp),
            contentAlignment = Alignment.Center
        ) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxSize(),
                progress = item.baseStat * 1f / 255f,
                color = getStatColor(item.name),
                trackColor = colorResource(id = R.color.white),
                strokeCap = StrokeCap.Round
            )
            Text(
                modifier = Modifier,
                text = String.format(STAT_FORMAT,item.baseStat),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}