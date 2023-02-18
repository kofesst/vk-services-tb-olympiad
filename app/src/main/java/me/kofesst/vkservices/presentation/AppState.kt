package me.kofesst.vkservices.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

val LocalAppState = compositionLocalOf<AppState> {
    error("AppState not initialized")
}

@Stable
class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
)

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = AppState(
    navController = navController,
    coroutineScope = coroutineScope
)