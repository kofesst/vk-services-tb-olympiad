package me.kofesst.vkservices.presentation.screens.list

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NavigateNext
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.gson.Gson
import me.kofesst.vkservices.domain.models.AppService
import me.kofesst.vkservices.presentation.LocalAppState
import me.kofesst.vkservices.presentation.navigation.NavRoutes
import me.kofesst.vkservices.ui.components.RemoteStateBox

@Composable
fun ServicesListScreen(
    modifier: Modifier = Modifier,
    viewModel: ServicesListViewModel,
) {
    val navController = LocalAppState.current.navController
    val listState by viewModel.listState
    RemoteStateBox(
        modifier = modifier,
        state = listState,
        onRetryClick = viewModel::fetchServices
    ) { appServices ->
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            itemsIndexed(appServices) { index, appService ->
                AppServiceItem(
                    modifier = Modifier.fillMaxWidth(),
                    appService = appService
                ) {
                    val serviceJson = Uri.encode(Gson().toJson(appService))
                    navController.navigate(
                        route = NavRoutes.ServiceDetails.route
                            .replace("{service}", serviceJson)
                    )
                }
                if (index != appServices.lastIndex) {
                    Divider(modifier = Modifier.padding(start = 88.dp))
                }
            }
        }
    }
}

@Composable
private fun AppServiceItem(
    modifier: Modifier = Modifier,
    appService: AppService,
    onClick: () -> Unit,
) {
    val context = LocalContext.current
    Box(modifier = modifier.clickable(onClick = onClick)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .crossfade(true)
                    .data(appService.iconUrl)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = appService.name,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.weight(1.0f))
            Icon(
                imageVector = Icons.Outlined.NavigateNext,
                contentDescription = null
            )
        }
    }
}