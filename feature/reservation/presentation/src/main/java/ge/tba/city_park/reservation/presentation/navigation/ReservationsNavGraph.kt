package ge.tba.city_park.reservation.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tba.city_park.reservation.presentation.screen.ReservationsScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.reservationsNavGraph(
    onShowSnackBar: (String) -> Unit,
    navigateToMap: () -> Unit,

) {
    composable<ReservationsRoute> {
        ReservationsScreenRoot(
            navigateToMap = navigateToMap,
            onShowSnackBar = onShowSnackBar
        )
    }
}

@Serializable
data object ReservationsRoute