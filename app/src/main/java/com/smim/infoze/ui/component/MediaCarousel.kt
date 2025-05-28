package com.smim.infoze.ui.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun MediaCarousel(
    imageResList: List<Int>,
    videoResId: Int?,
    onImageClick: (Int) -> Unit,
    onVideoClick: () -> Unit
) {
    val context = LocalContext.current
    val items = remember {
        when {
            videoResId != null && imageResList.isNotEmpty() -> listOf(null) + imageResList
            videoResId != null -> listOf(null)
            else -> imageResList
        }
    }

    // zabezpieczenie przed pustą listą
    if (items.isEmpty()) return

    val pagerState = rememberPagerState { Int.MAX_VALUE }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) { index ->
        val actualIndex = index % items.size
        val item = items[actualIndex]

        if (item == null && videoResId != null) {
            // Wideo
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onVideoClick() }
            ) {
                VideoPlayer(context, videoResId)
            }
        } else {
            // Zdjęcie
            Image(
                painter = painterResource(id = item!!),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        val imageIndex = actualIndex - if (videoResId != null) 1 else 0
                        onImageClick(imageIndex)
                    },
                contentScale = ContentScale.Crop
            )
        }
    }
}
