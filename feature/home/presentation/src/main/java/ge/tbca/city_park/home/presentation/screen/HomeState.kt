package ge.tbca.city_park.home.presentation.screen

import ge.tbca.city_park.core.ui.util.GenericString
import ge.tbca.city_park.home.presentation.model.ReservationUi

data class HomeState(
    val userBalance: Double? = null,
    val isLoading: Boolean = false,
    val activeReservation: ReservationUi? = null,
    val error: GenericString? = null,
    val showActiveReservationBottomSheet: Boolean = false,
    val showParkingFinishDialog: Boolean = false
){
    val clickEnabled:Boolean
        get () = !isLoading
}