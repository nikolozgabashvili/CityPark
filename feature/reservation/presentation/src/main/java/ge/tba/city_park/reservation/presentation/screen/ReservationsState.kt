package ge.tba.city_park.reservation.presentation.screen

import ge.tba.city_park.reservation.presentation.model.ReservationUi

data class ReservationsState(
    val reservationsList: List<ReservationUi> = emptyList(),
    val isLoading: Boolean = false
)