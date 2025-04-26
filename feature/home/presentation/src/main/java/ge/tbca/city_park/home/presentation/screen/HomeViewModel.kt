package ge.tbca.city_park.home.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.user.domain.usecase.FetchUserInfoUseCase
import ge.tbca.city_park.user.domain.usecase.GetUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val fetchUserInfoUseCase: FetchUserInfoUseCase
) :
    BaseViewModel<HomeScreenState, HomeScreenEffect, HomeScreenEvent>(HomeScreenState()) {

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
                    sendSideEffect(HomeScreenEffect.Error(error))
                }

            }
        }
    }

    override fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.Refresh -> fetchUserInfo()

            is HomeScreenEvent.NavigateToAddBalance -> navigateToAddBalance()
            is HomeScreenEvent.NavigateToProfile -> navigateToProfile()
        }

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