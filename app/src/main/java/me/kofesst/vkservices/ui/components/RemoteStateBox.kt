package me.kofesst.vkservices.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.kofesst.vkservices.domain.utils.RemoteResponse
import me.kofesst.vkservices.presentation.utils.RemoteState

@Composable
fun <T : Any> RemoteStateBox(
    modifier: Modifier = Modifier,
    state: RemoteState<T>,
    onRetryClick: () -> Unit,
    content: @Composable (T) -> Unit,
) {
    when (state) {
        is RemoteState.Default,
        is RemoteState.Fetching,
        -> {
            Box(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is RemoteState.Failed -> {
            FailedContent(
                message = "Произошла непредвиденная ошибка",
                actionText = "Повторить загрузку",
                onActionClick = onRetryClick
            )
        }

        is RemoteState.Loaded -> {
            when (val response = state.response) {
                is RemoteResponse.Error -> {
                    FailedContent(
                        message = "Произошла непредвиденная ошибка",
                        actionText = "Повторить загрузку",
                        onActionClick = onRetryClick
                    )
                }

                is RemoteResponse.Ok -> {
                    content(response.body!!)
                }
            }
        }
    }
}