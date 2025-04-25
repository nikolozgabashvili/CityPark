package ge.tba.city_park.reservation.presentation.screen

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ReservationsViewModel @Inject constructor():
    BaseViewModel<ReservationsState, ReservationsEffect, ReservationsEvent>(ReservationsState()) {

    override fun onEvent(event: ReservationsEvent) {
        when(event) {
            else -> {}
        }
    }
}