package com.lkw1120.pokedex.core.component

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lkw1120.pokedex.core.common.R

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.transparent)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = colorResource(id = R.color.red)
        )
    }
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    message: String,
) {

    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.transparent)),
        contentAlignment = Alignment.Center
    ) {
        AlertDialog(
            modifier = Modifier,
            title = {
                Text(
                    text = stringResource(id = R.string.error_title)
                )
            },
            text = {
                Text(
                    modifier = Modifier
                        .defaultMinSize(minHeight = 56.dp),
                    text = message
                )
           },
            confirmButton = {
                Text(
                    modifier = Modifier
                        .clickable { activity?.recreate() },
                    text = stringResource(id = R.string.error_reload),
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.gray)
                    )
                )
            },
            onDismissRequest = {

            },
        )
    }
}

@Composable
fun ListEmptyScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.transparent)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .size(96.dp),
            painter = painterResource(id = R.drawable.ic_pokeball),
            contentDescription = null
        )
        Spacer(
            modifier = Modifier
                .height(12.dp)
        )
        Text(
            text = stringResource(id = R.string.list_is_empty),
            style = TextStyle(
                fontSize = 21.sp,
                fontWeight = FontWeight.SemiBold,
                color = colorResource(id = R.color.white)
            )
        )
    }
}