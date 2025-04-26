package ge.tbca.city_park.app.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
    onSuccessfulAuth: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    CityParkApplication(
        appState = appState,
        startDestination = startDestination,
        snackbarHostState = snackbarHostState,
        onSuccessfulAuth = onSuccessfulAuth
    )

}

@Composable
fun CityParkApplication(
    appState: AppState,
    startDestination: KClass<*>,
    snackbarHostState: SnackbarHostState,
    onSuccessfulAuth: () -> Unit,
) {

    val currentDestination = appState.currentDestination
    val topLevelDestinations = appState.topLevelDestinations
    val currentTopLevelDestination = appState.currentTopLevelDestination

    val coroutineScope = appState.coroutineScope

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                snackbarHostState,
                modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
            )
        },
        bottomBar = {
            CityParkBottomNavigation(
                destinations = topLevelDestinations,
                currentDestination = currentDestination,
                onNavigateToDestination = { destination ->
                    appState.navigateToTopLevelDestination(destination)
                }
            )
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
                onSuccessfulAuth = onSuccessfulAuth,
                onShowSnackBar = { message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                })
        }

    }
}

fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any {
        it.hasRoute(route)
    } ?: false