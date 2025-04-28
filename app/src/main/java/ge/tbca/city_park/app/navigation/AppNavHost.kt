package ge.tbca.city_park.app.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import ge.tba.city_park.reservation.presentation.navigation.CreateReservationRoute
import ge.tba.city_park.reservation.presentation.navigation.reservationsNavGraph
import ge.tbca.city_park.app.ui.AppState
import ge.tbca.city_park.auth.presentation.navigation.ChangePasswordScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.RecoverPasswordScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.RegisterScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.authNavGraph
import ge.tbca.city_park.cars.presentation.navigation.AddCarScreenRoute
import ge.tbca.city_park.cars.presentation.navigation.CarsScreenRoute
import ge.tbca.city_park.cars.presentation.navigation.carNavGraph
import ge.tbca.city_park.home.presentation.navigation.homeNavGraph
import ge.tbca.city_park.more.presentation.navigation.moreNavGraph
import ge.tbca.city_park.parking.presentation.navigation.MapScreenRoute
import ge.tbca.city_park.parking.presentation.navigation.mapNavGraph
import ge.tbca.city_park.payment.presentation.navigation.AddBalanceScreenRoute
import ge.tbca.city_park.payment.presentation.navigation.AddCardScreenRoute
import ge.tbca.city_park.payment.presentation.navigation.CardsScreenRoute
import ge.tbca.city_park.payment.presentation.navigation.paymentNavGraph
import ge.tbca.city_park.settings.presentation.navigation.LanguageSettingsScreenRoute
import ge.tbca.city_park.settings.presentation.navigation.SettingsScreenRoute
import ge.tbca.city_park.settings.presentation.navigation.ThemeSettingsScreenRoute
import ge.tbca.city_park.settings.presentation.navigation.settingsNavGraph
import ge.tbca.city_park.user.presentation.navigation.ProfileScreenRoute
import ge.tbca.city_park.user.presentation.navigation.profileNavGraph
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
        enterTransition = { fadeIn(tween(400)) },
        exitTransition = { fadeOut(tween(400)) },
        popEnterTransition = { fadeIn(tween(400)) },
        popExitTransition = { fadeOut(tween(400)) }


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
            navigateToAddBalance = {
                navController.navigate(AddBalanceScreenRoute)
            },
            navigateToProfile = {
                navController.navigate(ProfileScreenRoute)
            },
            navigateToAddReservation = {
                navController.navigate(CreateReservationRoute)
            },

            navigateToCards = {
                navController.navigate(CardsScreenRoute)
            }
        )


        reservationsNavGraph(
            onShowSnackBar = onShowSnackBar,
            navigateToMap = {
                navController.navigate(MapScreenRoute){
                    popUpTo(CreateReservationRoute) {
                        inclusive = true
                    }
                }
            },
            navigateBack = { navController.navigateUp() },
            navigateToCreateReservation = { navController.navigate(CreateReservationRoute) },
            navigateToAddCar = { navController.navigate(AddCarScreenRoute) }
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

        paymentNavGraph(
            navigateBack = {
                navController.navigateUp()
            },
            onShowSnackBar = onShowSnackBar,
            navigateToAddCard = {
                navController.navigate(AddCardScreenRoute)
            }
        )

        mapNavGraph(
            onShowSnackBar = onShowSnackBar,
            navigateToAddCar = {
                navController.navigate(AddCarScreenRoute)
            },
            navigateToAddBalance = {
                navController.navigate(AddBalanceScreenRoute)
            },
        )

        moreNavGraph(
            navigateToSettings = {
                navController.navigate(SettingsScreenRoute)
            },
            navigateToCars = {
                navController.navigate(CarsScreenRoute)
            },
            navigateToCards = {
                navController.navigate(CardsScreenRoute)
            },
            navigateToProfile = {
                navController.navigate(ProfileScreenRoute)
            }
        )

        profileNavGraph(
            onShowSnackBar = onShowSnackBar,
            navigateBack = {
                navController.navigateUp()
            },
            navigateToChangePassword = {
                navController.navigate(ChangePasswordScreenRoute)
            }
        )
    }
}