package ge.tbca.city_park.home.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.home.presentation.mapper.toPresenter
import ge.tbca.city_park.reservation.domain.model.FinishReservationRequest
import ge.tbca.city_park.reservation.domain.usecase.FinishReservationUseCase
import ge.tbca.city_park.reservation.domain.usecase.GetActiveReservationUseCase
import ge.tbca.city_park.user.domain.usecase.FetchUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUserInfoUseCase: FetchUserInfoUseCase,
    private val getActiveReservationUseCase: GetActiveReservationUseCase,
    private val finishParkingUseCase: FinishReservationUseCase
) :
    BaseViewModel<HomeState, HomeEffect, HomeEvent>(HomeState()) {

    init {
        fetchUserInfo()
        checkActiveReservation()
    }


    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Refresh -> refresh()
            is HomeEvent.NavigateToAddBalance -> navigateToAddBalance()
            is HomeEvent.NavigateToNotificationsScreen -> navigateToNotificationsScreen()
            is HomeEvent.OnFinishRequest -> showDialog()
            is HomeEvent.DismissParkingDialog -> dismissDialog()
            is HomeEvent.FinishParking -> finishParking()
            is HomeEvent.NavigateToCars -> navigateToCars()
            is HomeEvent.NavigateToAddReservation -> navigateToAddReservation()
            is HomeEvent.NavigateToCards -> navigateToCards()
        }

    }

    private fun navigateToCards() {
        viewModelScope.launch {
            sendSideEffect(HomeEffect.NavigateToCards)
        }
    }

    private fun navigateToAddReservation() {
        viewModelScope.launch {
            sendSideEffect(HomeEffect.NavigateToAddReservation)
        }
    }

    private fun navigateToCars() {
        viewModelScope.launch {
            sendSideEffect(HomeEffect.NavigateToCars)
        }
    }

    private fun finishParking() {
        dismissDialog()
        state.activeReservation?.let { reservation ->

            viewModelScope.launch {
                finishParkingUseCase(
                    reservation = FinishReservationRequest(
                        reservationId = reservation.id
                    )
                ).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }

                    when (resource) {
                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(HomeEffect.Error(error))
                        }

                        is Resource.Success -> {
                            refresh()
                        }

                        Resource.Loading -> Unit
                    }

                }
            }
        }
    }

    private fun dismissDialog() {
        updateState { copy(showParkingFinishDialog = false) }
    }

    private fun showDialog() {
        updateState { copy(showParkingFinishDialog = true) }

    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            fetchUserInfoUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading(), error = null) }

                when (resource) {
                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState { copy(error = error) }
                    }

                    is Resource.Success -> {
                        updateState { copy(userBalance = resource.data.parkingBalance) }
                    }

                    Resource.Loading -> Unit
                }

            }
        }
    }


    private fun checkActiveReservation() {
        viewModelScope.launch {
            getActiveReservationUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }

                when (resource) {
                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState { copy(error = error) }
                    }

                    is Resource.Success -> {
                        val reservation = resource.data?.toPresenter()
                        updateState { copy(activeReservation = reservation) }

                    }

                    Resource.Loading -> Unit
                }

            }
        }

    }

    private fun refresh() {
        fetchUserInfo()
        checkActiveReservation()
    }

    private fun navigateToNotificationsScreen() {
        viewModelScope.launch {
            sendSideEffect(HomeEffect.NavigateToNotificationsScreen)
        }
    }

    private fun navigateToAddBalance() {
        viewModelScope.launch {
            sendSideEffect(HomeEffect.NavigateToAddBalance)
        }
    }

}