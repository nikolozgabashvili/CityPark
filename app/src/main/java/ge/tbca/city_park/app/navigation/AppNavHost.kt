package ge.tbca.city_park.app.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import ge.tbca.city_park.reservation.presentation.navigation.CreateReservationRoute
import ge.tbca.city_park.reservation.presentation.navigation.reservationsNavGraph
import ge.tbca.city_park.app.ui.AppState
import ge.tbca.city_park.auth.presentation.navigation.ChangePasswordScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.RecoverPasswordScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.RegisterScreenRoute
import ge.tbca.city_park.auth.presentation.navigation.authNavGraph
import ge.tbca.city_park.cars.presentation.navigation.AddCarScreenRoute
import ge.tbca.city_park.cars.presentation.navigation.CarsScreenRoute
import ge.tbca.city_park.cars.presentation.navigation.carNavGraph
import ge.tbca.city_park.fines.presentation.navigation.FinesScreenRoute
import ge.tbca.city_park.fines.presentation.navigation.finesNavGraph
import ge.tbca.city_park.home.presentation.navigation.homeNavGraph
import ge.tbca.city_park.messaging.presentation.navigation.NotificationsScreenRoute
import ge.tbca.city_park.messaging.presentation.navigation.notificationsNavGraph
import ge.tbca.city_park.more.presentation.navigation.moreNavGraph
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
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None }


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
            navigateToNotificationsScreen = {
                navController.navigate(NotificationsScreenRoute)
            },
            navigateToAddReservation = {
                navController.navigate(CreateReservationRoute)
            },
            navigateToCards = {
                navController.navigate(CardsScreenRoute)
            },
            navigateToFines = {
                navController.navigate(FinesScreenRoute)
            }
        )


        reservationsNavGraph(
            onShowSnackBar = onShowSnackBar,
            navigateToMap = {
                navController.popBackStack()
                appState.navigateToTopLevelDestination(TopLevelDestination.MAP)
            },
            navigateBack = { navController.navigateUp() },
            navigateToCreateReservation = { navController.navigate(CreateReservationRoute) },
            navigateToAddCar = { navController.navigate(AddCarScreenRoute) },
            navigateToAddBalance = { navController.navigate(AddBalanceScreenRoute) }
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

        notificationsNavGraph(
            onShowSnackBar = onShowSnackBar,
            navigateBack = {
                navController.navigateUp()
            }
        )

        finesNavGraph(
            onShowSnackBar = onShowSnackBar,
            navigateToFineDetail = {},
            navigateBack = { navController.navigateUp() }
        )
    }
}