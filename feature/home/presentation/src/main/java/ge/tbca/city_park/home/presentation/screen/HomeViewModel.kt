package ge.tbca.city_park.home.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.reservation.domain.usecase.GetActiveReservationUseCase
import ge.tbca.city_park.user.domain.usecase.FetchUserInfoUseCase
import ge.tbca.city_park.user.domain.usecase.GetUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val fetchUserInfoUseCase: FetchUserInfoUseCase,
    private val getActiveReservationUseCase: GetActiveReservationUseCase,
) :
    BaseViewModel<HomeState, HomeEffect, HomeEvent>(HomeState()) {

    init {
        fetchUserInfo()
        observeUserInfo()
    }

    private fun observeUserInfo() {
        viewModelScope.launch {
            getUserInfoUseCase().collect { userInfo ->
                userInfo?.let {
                    updateState { copy(userBalance = userInfo.parkingBalance) }
                }
            }
        }
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
            is HomeEvent.Refresh -> fetchUserInfo()

            is HomeEvent.NavigateToAddBalance -> navigateToAddBalance()
            is HomeEvent.NavigateToProfile -> navigateToProfile()
        }

    }

    private fun navigateToProfile() {
        viewModelScope.launch {
            sendSideEffect(HomeEffect.NavigateToProfile)
        }
    }

    private fun navigateToAddBalance() {
        viewModelScope.launch {
            sendSideEffect(HomeEffect.NavigateToAddBalance)
        }
    }

}