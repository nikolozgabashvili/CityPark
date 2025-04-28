package ge.tba.city_park.reservation.presentation.screen.reservations

import ge.tba.city_park.reservation.presentation.model.ReservationUi
import ge.tbca.city_park.core.ui.util.GenericString

data class ReservationsState(
    val reservationsList: List<ReservationUi> = emptyList(),
    val isLoading: Boolean = false,
    val error:GenericString? = null,
    val noReservations:Boolean = false
)