package ge.tbca.city_park.home.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
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

    private fun fetchUserInfo() {
        viewModelScope.launch {
            fetchUserInfoUseCase().collect {
                updateState { copy(isLoading = it.isLoading(), error = null) }

                if (it is Resource.Error) {
                    val error = it.error.toGenericString()
                    updateState { copy(error = error) }
                    sendSideEffect(HomeEffect.Error(error))
                }

            }
        }
    }

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeScreenEvent.Refresh -> refresh()
            is HomeScreenEvent.NavigateToAddBalance -> navigateToAddBalance()
            is HomeScreenEvent.NavigateToProfile -> navigateToProfile()
            is HomeScreenEvent.OnFinishRequest -> showDialog()
            is HomeScreenEvent.DismissParkingDialog -> dismissDialog()
            is HomeScreenEvent.FinishParking -> finishParking()
            is HomeScreenEvent.NavigateToCars -> navigateToCars()
            is HomeScreenEvent.NavigateToAddReservation -> navigateToAddReservation()
            is HomeScreenEvent.NavigateToCards -> navigateToCards()
        }

    }

    private fun navigateToCards() {
        viewModelScope.launch {
            sendSideEffect(HomeScreenEffect.NavigateToCards)
        }
    }

    private fun navigateToAddReservation() {
        viewModelScope.launch {
            sendSideEffect(HomeScreenEffect.NavigateToAddReservation)
        }
    }

    private fun navigateToCars() {
        viewModelScope.launch {
            sendSideEffect(HomeScreenEffect.NavigateToCars)
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
                            sendSideEffect(HomeScreenEffect.Error(error))
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

    private fun navigateToProfile() {
        viewModelScope.launch {
            sendSideEffect(HomeScreenEffect.NavigateToProfile)
        }
    }

    private fun navigateToAddBalance() {
        viewModelScope.launch {
            sendSideEffect(HomeScreenEffect.NavigateToAddBalance)
        }
    }

}