package ge.tba.city_park.reservation.presentation.screen.reservations

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface ReservationsEffect {
    data class Error(val message: GenericString) : ReservationsEffect
    data object NavigateToAddReservation : ReservationsEffect
    data object ReservationsRefreshed : ReservationsEffect
}