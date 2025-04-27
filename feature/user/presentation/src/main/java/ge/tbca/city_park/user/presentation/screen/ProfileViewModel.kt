package ge.tbca.city_park.user.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.messaging.domain.usecase.UpdateMessagingTokenUseCase
import ge.tbca.city_park.user.domain.usecase.FetchUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fetchUserInfoUseCase: FetchUserInfoUseCase,
    private val updateMessagingTokenUseCase: UpdateMessagingTokenUseCase
) : BaseViewModel<ProfileState, ProfileEffect, ProfileEvent>(ProfileState()) {

    init {
        fetchUserInfo()
    }

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.BackButtonClicked -> navigateBack()
            is ProfileEvent.ChangePasswordButtonClicked -> navigateToChangePassword()
            is ProfileEvent.SignOutButtonClicked -> signOut()
        }
    }

    private fun fetchUserInfo() {
        viewModelScope.launch {
            fetchUserInfoUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }
                when (resource) {
                    is Resource.Success -> {
                        val userInfo = resource.data
                        updateState {
                            copy(
                                userEmail = userInfo.email,
                                userBalance = userInfo.parkingBalance
                            )
                        }
                    }

                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        sendSideEffect(ProfileEffect.Error(error))
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(ProfileEffect.NavigateBack)
        }
    }

    private fun navigateToChangePassword() {
        viewModelScope.launch {
            sendSideEffect(ProfileEffect.NavigateToChangePassword)
        }
    }

    private fun signOut() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            when (val request = updateMessagingTokenUseCase(null)) {
                is Resource.Success -> Unit

                is Resource.Error -> {
                    updateState { copy(isLoading = false) }
                    sendSideEffect(ProfileEffect.Error(request.error.toGenericString()))
                }

                is Resource.Loading -> Unit
            }
        }
    }
}