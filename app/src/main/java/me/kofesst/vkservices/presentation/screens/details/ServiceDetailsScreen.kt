package me.kofesst.vkservices.presentation.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.OpenInNew
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import me.kofesst.vkservices.domain.models.AppService
import me.kofesst.vkservices.presentation.LocalAppState
import me.kofesst.vkservices.presentation.utils.openLinkInBrowser
import me.kofesst.vkservices.ui.components.FailedContent
import me.kofesst.vkservices.ui.components.RemoteImage

@Composable
fun ServiceDetailsScreen(
    modifier: Modifier = Modifier,
    appService: AppService?,
) {
    val navController = LocalAppState.current.navController
    if (appService != null) {
        ServiceDetails(
            modifier = modifier.padding(top = 20.dp),
            appService = appService
        )
    } else {
        FailedContent(
            message = "Произошла непредвиденная ошибка",
            actionText = "Вернуться назад"
        ) {
            navController.navigateUp()
        }
    }
}

@Composable
private fun ServiceDetails(
    modifier: Modifier = Modifier,
    appService: AppService,
    iconSize: Dp = 96.dp,
) {
    val context = LocalContext.current
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Column(modifier = Modifier.padding(top = iconSize / 2)) {
            Spacer(modifier = Modifier.height(iconSize / 2))
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = appService.name,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = appService.description,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = 0.7f
                    )
                )
                ExtendedFloatingActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        openLinkInBrowser(context, appService.serviceUrl)
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Outlined.OpenInNew,
                            contentDescription = null
                        )
                    },
                    text = {
                        Text(
                            text = "Открыть в браузере",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                )
            }
        }
        RemoteImage(
            url = appService.iconUrl,
            modifier = Modifier.size(iconSize)
        )
    }
}