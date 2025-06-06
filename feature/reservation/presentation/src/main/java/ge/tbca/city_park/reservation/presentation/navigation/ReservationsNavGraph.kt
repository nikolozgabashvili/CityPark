package ge.tbca.city_park.reservation.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tbca.city_park.reservation.presentation.screen.create_reservation.CreateReservationScreenRoot
import ge.tbca.city_park.reservation.presentation.screen.reservations.ReservationsScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.reservationsNavGraph(
    onShowSnackBar: (String) -> Unit,
    navigateToMap: () -> Unit,
    navigateBack: () -> Unit,
    navigateToCreateReservation: () -> Unit,
    navigateToAddBalance: () -> Unit,
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
            navigateToAddCar = navigateToAddCar,
            navigateToAddBalance = navigateToAddBalance
        )
    }
}

@Serializable
data object ReservationsRoute

@Serializable
data object CreateReservationRoute