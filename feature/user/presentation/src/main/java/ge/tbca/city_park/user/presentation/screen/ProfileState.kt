package ge.tbca.city_park.user.presentation.screen

import ge.tba.city_park.reservation.presentation.model.ReservationUi
import ge.tbca.city_park.core.ui.util.GenericString

data class ProfileState(
    val userEmail: String? = null,
    val userBalance: Double? = null,
    val isLoading: Boolean = false,
    val showActiveReservationDialog: Boolean = false,
    val error: GenericString? = null,
    val activeReservation: ReservationUi? = null
)