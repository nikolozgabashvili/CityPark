package ge.tbca.city_park.app.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import ge.tbca.city_park.app.navigation.TopLevelDestination
import ge.tbca.city_park.home.presentation.navigation.HomeScreenRoute
import ge.tbca.city_park.settings.presentation.navigation.SettingsScreenRoute
import kotlinx.coroutines.CoroutineScope


@Composable
fun rememberAppState(
    navHostController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): AppState {

    return remember(navHostController) { AppState(navHostController, coroutineScope) }

}


data class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
){
    private val previousDestination = mutableStateOf<NavDestination?>(null)

    val currentDestination: NavDestination?
        @Composable get() {
            val currentEntry = navController.currentBackStackEntryFlow
                .collectAsState(initial = null)

            return currentEntry.value?.destination.also { destination ->
                if (destination != null) {
                    previousDestination.value = destination
                }
            } ?: previousDestination.value
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries


    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                TopLevelDestination.HOME -> navController.navigate(HomeScreenRoute, topLevelNavOptions)
                TopLevelDestination.SETTINGS -> navController.navigate(SettingsScreenRoute, topLevelNavOptions)
            }

    }

}