package me.kofesst.vkservices.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    url: String,
) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context)
            .crossfade(true)
            .data(url)
            .build(),
        contentDescription = null,
        modifier = modifier
    )
}