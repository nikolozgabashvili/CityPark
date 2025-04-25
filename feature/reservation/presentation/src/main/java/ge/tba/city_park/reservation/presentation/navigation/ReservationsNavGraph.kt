package ge.tba.city_park.reservation.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ge.tba.city_park.reservation.presentation.screen.ReservationsScreenRoot
import kotlinx.serialization.Serializable

fun NavGraphBuilder.reservationsNavGraph() {
    composable<ReservationsRoute> {
        ReservationsScreenRoot()
    }
}

@Serializable
data object ReservationsRoute