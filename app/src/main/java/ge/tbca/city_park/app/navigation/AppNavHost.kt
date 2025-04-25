package ge.tbca.city_park.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import ge.tba.city_park.reservation.presentation.navigation.reservationsNavGraph
import ge.tbca.city_park.app.ui.AppState
import ge.tbca.city_park.auth.presentation.navigation.RecoverPasswordScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.RegisterScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.authNavGraph
import ge.tbca.city_park.cars.presentation.navigation.AddCarScreenRoute
import ge.tbca.city_park.cars.presentation.navigation.CarsScreenRoute
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
    onShowSnackBar: (String) -> Unit,
    onSuccessfulAuth: () -> Unit,
) {
    val navController = appState.navController


    NavHost(
        navController = navController,
        startDestination = startDestination,

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
            },
            onSuccessfulAuth = onSuccessfulAuth
        )

        homeNavGraph(
            navigateToCars = {
                navController.navigate(CarsScreenRoute)
            },
            onShowSnackBar = onShowSnackBar,
        )


        reservationsNavGraph(
            onShowSnackBar = onShowSnackBar,
            navigateToMap = {

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
            onShowSnackBar = onShowSnackBar,
            navigateToAddCar = {
                navController.navigate(AddCarScreenRoute)
            }
        )

    }

}