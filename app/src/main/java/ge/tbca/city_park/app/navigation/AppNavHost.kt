package ge.tbca.city_park.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import ge.tbca.city_park.app.ui.AppState
import ge.tbca.city_park.auth.presentation.navigation.RecoverPasswordScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.RegisterScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.authNavGraph
import ge.tbca.city_park.cars.presentation.navigation.AddCarScreenRoute
import ge.tbca.city_park.cars.presentation.navigation.carNavGraph
import ge.tbca.city_park.home.presentation.navigation.homeNavGraph
import ge.tbca.city_park.settings.presentation.navigation.LanguageSettingsScreenRoute
import ge.tbca.city_park.settings.presentation.navigation.ThemeSettingsScreenRoute
import ge.tbca.city_park.settings.presentation.navigation.settingsNavGraph
import kotlin.reflect.KClass

@Composable
fun AppNavHost(
    appState: AppState,
    startDestination: KClass<*>,
    onShowSnackBar: (String) -> Unit
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End, tween(500)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start, tween(500)
            )
        }

    ) {
        authNavGraph(
            onShowSnackBar = onShowSnackBar,
            navigateToRegister = {
                navController.navigate(RegisterScreenRoute)
            },
            navigateToRecovery = {
                navController.navigate(RecoverPasswordScreenRoute)
            },
            navigateBack = {
                navController.navigateUp()
            }
        )

        homeNavGraph(
            navigateToAddCar = {
                navController.navigate(AddCarScreenRoute)
            }
        )

        settingsNavGraph(
            navigateBack = {
                navController.navigateUp()
            },
            navigateToLanguageSettings = {
                navController.navigate(LanguageSettingsScreenRoute)

            },
            navigateToThemeSettings = {
                navController.navigate(ThemeSettingsScreenRoute)
            }
        )

        carNavGraph(
            navigateBack = { navController.navigateUp() },
            onShowSnackBar = onShowSnackBar
        )

    }

}