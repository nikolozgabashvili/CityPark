package ge.tba.city_park.reservation.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tba.city_park.reservation.presentation.screen.create_reservation.CreateReservationScreenRoot
import ge.tba.city_park.reservation.presentation.screen.reservations.ReservationsScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.reservationsNavGraph(
    onShowSnackBar: (String) -> Unit,
    navigateToMap: () -> Unit,
    navigateBack: () -> Unit,
    navigateToCreateReservation: () -> Unit,
    navigateToAddCar: () -> Unit
) {
    composable<ReservationsRoute> {
        ReservationsScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateToCreateReservation = navigateToCreateReservation
        )
    }

    composable<CreateReservationRoute> {
        CreateReservationScreenRoot(
            onShowSnackBar = onShowSnackBar,
            navigateBack = navigateBack,
            navigateToMap = navigateToMap,
            navigateToAddCar = navigateToAddCar
        )
    }
}

@Serializable
data object ReservationsRoute

@Serializable
data object CreateReservationRoute