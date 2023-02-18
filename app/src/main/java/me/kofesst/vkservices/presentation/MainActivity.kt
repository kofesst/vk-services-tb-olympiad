package me.kofesst.vkservices.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import dagger.hilt.android.AndroidEntryPoint
import me.kofesst.vkservices.presentation.navigation.NavRoutes
import me.kofesst.vkservices.ui.theme.VKServicesTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKServicesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val appState = rememberAppState()
                    CompositionLocalProvider(
                        LocalAppState provides appState
                    ) {
                        AppScaffold {
                            AppNavHost(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(it)
                            )
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun AppNavHost(
        modifier: Modifier = Modifier,
    ) {
        val appState = LocalAppState.current
        NavHost(
            navController = appState.navController,
            startDestination = NavRoutes.ServicesList.route,
            modifier = modifier
        ) {
            NavRoutes.values().forEach { navRoute ->
                navRoute.content(this@NavHost, this@MainActivity)
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AppScaffold(
        content: @Composable (PaddingValues) -> Unit,
    ) {
        Scaffold(
            topBar = {
                AppTopBar()
            },
            modifier = Modifier.fillMaxSize()
        ) {
            content(it)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun AppTopBar() {
        val navController = LocalAppState.current.navController
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentRoute = currentBackStack?.destination?.route
        val currentScreen = NavRoutes.values().firstOrNull { navRoute ->
            navRoute.route == currentRoute
        } ?: NavRoutes.ServicesList
        TopAppBar(
            title = {
                Text(
                    text = currentScreen.title,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            navigationIcon = {
                if (currentScreen.hasBackButton) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowBackIosNew,
                            contentDescription = null
                        )
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}