package me.kofesst.vkservices.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navArgument
import me.kofesst.vkservices.presentation.MainActivity
import me.kofesst.vkservices.presentation.screens.details.extras.AppServiceNavType

enum class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val title: String,
    val hasBackButton: Boolean = false,
    val content: NavGraphBuilder.(MainActivity) -> Unit,
) {
    ServicesList(
        route = "/services",
        title = "Список сервисов",
        content = { servicesList(it) }
    ),
    ServiceDetails(
        route = "/services/{service}",
        title = "Детали сервиса",
        hasBackButton = true,
        arguments = listOf(
            navArgument("service") {
                type = AppServiceNavType()
            }
        ),
        content = { serviceDetails() }
    )
}