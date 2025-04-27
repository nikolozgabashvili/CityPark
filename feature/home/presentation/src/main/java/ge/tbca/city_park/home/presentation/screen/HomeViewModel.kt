package ge.tbca.city_park.home.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.reservation.domain.usecase.GetActiveReservationUseCase
import ge.tbca.city_park.user.domain.usecase.FetchUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUserInfoUseCase: FetchUserInfoUseCase,
    private val getActiveReservationUseCase: GetActiveReservationUseCase,
) :
    BaseViewModel<HomeScreenState, HomeScreenEffect, HomeScreenEvent>(HomeScreenState()) {

    init {
        fetchUserInfo()
    }




    override fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.Refresh -> refresh()

            is HomeScreenEvent.NavigateToAddBalance -> navigateToAddBalance()
            is HomeScreenEvent.NavigateToProfile -> navigateToProfile()
        }

    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            fetchUserInfoUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading(), error = null) }

                when (resource) {
                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState { copy(error = error) }
                        sendSideEffect(HomeScreenEffect.Error(error))
                    }

                    Resource.Loading -> Unit
                    is Resource.Success -> {
                        updateState { copy(userBalance = resource.data.parkingBalance) }
                    }
                }

            }
        }
    }

    private fun refresh() {
        fetchUserInfo()
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