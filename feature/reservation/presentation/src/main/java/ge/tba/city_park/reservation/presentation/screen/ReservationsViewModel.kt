package ge.tba.city_park.reservation.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tba.city_park.reservation.presentation.mapper.toPresenter
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.reservation.domain.model.ReservationDomain
import ge.tbca.city_park.reservation.domain.usecase.GetAllReservationsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReservationsViewModel @Inject constructor(
    private val getAllReservationsUseCase: GetAllReservationsUseCase
) : BaseViewModel<ReservationsState, ReservationsEffect, ReservationsEvent>(ReservationsState()) {

    init {

        fetchReservations()

    }

    private fun fetchReservations() {
        viewModelScope.launch {
            getAllReservationsUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading() , error = null) }
                when (resource) {
                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        if (state.reservationsList.isEmpty()) {
                            updateState { copy(error = error) }
                        }
                        sendSideEffect(ReservationsEffect.Error(error))
                    }

                    is Resource.Success -> updateReservations(resource.data)

                    is Resource.Loading -> Unit
                }
            }
        }
    }

    override fun onEvent(event: ReservationsEvent) {
        when (event) {
            is ReservationsEvent.AddReservationButtonClicked -> {}
            ReservationsEvent.Refresh -> {
                fetchReservations()

            }
        }
    }

    private fun updateReservations(list: List<ReservationDomain>) {
        updateState {
            copy(
                reservationsList = list.toPresenter(),
                noReservations = list.isEmpty(),

            )
        }
    }
}