package ge.tbca.city_park.app.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import ge.tbca.city_park.app.navigation.AppNavHost
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

@Composable
fun CityParkApplication(
    appState: AppState,
    startDestination: KClass<*>,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    CityParkApplication(
        appState = appState,
        startDestination = startDestination,
        snackbarHostState = snackbarHostState
    )

}

@Composable
fun CityParkApplication(
    appState: AppState,
    startDestination: KClass<*>,
    snackbarHostState: SnackbarHostState
) {

    val currentDestination = appState.currentDestination
    val topLevelDestinations = appState.topLevelDestinations

    val coroutineScope = appState.coroutineScope

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                snackbarHostState,
                modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
            )
        },
        bottomBar = {
            AnimatedVisibility(
                visible = topLevelDestinations.any { destination ->
                    currentDestination?.isRouteInHierarchy(destination.route)==true
                },
                enter = fadeIn() + expandVertically(),
                exit = ExitTransition.None

            ) {
                NavigationBar {
                    topLevelDestinations.forEach { destination ->
                        val selected = currentDestination.isRouteInHierarchy(destination.route)
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                appState.navigateToTopLevelDestination(destination)
                            },
                            icon = {
                                Icon(
                                    imageVector = if (selected) destination.selectedIcon else destination.unselectedIcon,
                                    contentDescription = null
                                )
                            },

                            label = {
                                Text(
                                    text = stringResource(destination.iconTextId)
                                )
                            },
                            alwaysShowLabel = true,
                        )

                    }
                }
            }

        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .imePadding()
        ) {
            AppNavHost(
                appState = appState,
                startDestination = startDestination,
                onShowSnackBar = { message ->
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(message)
                }
            })
        }

    }
}

private fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false