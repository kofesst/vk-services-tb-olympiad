package me.kofesst.vkservices.presentation.navigation

import android.os.Build
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import me.kofesst.vkservices.presentation.MainActivity
import me.kofesst.vkservices.presentation.screens.details.ServiceDetailsScreen
import me.kofesst.vkservices.presentation.screens.details.extras.AppServiceExtra
import me.kofesst.vkservices.presentation.screens.list.ServicesListScreen

fun NavGraphBuilder.servicesList(mainActivity: MainActivity) {
    navRouteComposable(NavRoutes.ServicesList) {
        ServicesListScreen(
            viewModel = hiltViewModel(viewModelStoreOwner = mainActivity),
            modifier = Modifier.fillMaxSize()
        )
    }
}

fun NavGraphBuilder.serviceDetails() {
    navRouteComposable(NavRoutes.ServiceDetails) {
        @Suppress("DEPRECATION")
        val serviceExtra = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            it.arguments?.getParcelable("service", AppServiceExtra::class.java)
        } else {
            it.arguments?.getParcelable("service")
        }
        ServiceDetailsScreen(
            appService = serviceExtra?.toModel(),
            modifier = Modifier.fillMaxSize()
        )
    }
}

private fun NavGraphBuilder.navRouteComposable(
    navRoute: NavRoutes,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = navRoute.route,
        arguments = navRoute.arguments
    ) {
        content(it)
    }
}