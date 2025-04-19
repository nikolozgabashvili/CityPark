package ge.tbca.city_park.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import ge.tbca.city_park.app.ui.AppState
import ge.tbca.city_park.auth.presentation.navigation.LoginScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.RecoverPasswordScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.RegisterScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.authNavGraph

@Composable
fun AppNavHost(
    appState: AppState,
    onShowSnackBar: (String) -> Unit
) {
    val navController = appState.navHostController

    NavHost(
        navController = navController,
        startDestination = LoginScreenRoute,
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
            navigateToHome = {},
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
    }

}