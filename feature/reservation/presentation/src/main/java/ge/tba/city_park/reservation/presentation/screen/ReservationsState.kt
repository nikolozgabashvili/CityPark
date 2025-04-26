package ge.tba.city_park.reservation.presentation.screen

import ge.tba.city_park.reservation.presentation.model.ReservationUi
import ge.tbca.citi_park.core.ui.util.GenericString

data class ReservationsState(
    val reservationsList: List<ReservationUi> = emptyList(),
    val isLoading: Boolean = false,
    val error:GenericString? = null,
    val noReservations:Boolean = false
)