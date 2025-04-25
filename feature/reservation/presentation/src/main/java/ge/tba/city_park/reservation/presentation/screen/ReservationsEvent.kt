package ge.tba.city_park.reservation.presentation.screen

sealed interface ReservationsEvent {
    data object AddReservationButtonClicked : ReservationsEvent
}