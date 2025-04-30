package ge.tbca.city_park.user.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.auth.domain.usecase.SignOutUseCase
import ge.tbca.city_park.auth.presentation.extension.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.messaging.domain.usecase.DeleteMessagingTokenUseCase
import ge.tbca.city_park.reservation.domain.model.FinishReservationRequest
import ge.tbca.city_park.reservation.domain.usecase.FinishReservationUseCase
import ge.tbca.city_park.reservation.domain.usecase.GetActiveReservationUseCase
import ge.tbca.city_park.user.domain.usecase.FetchUserInfoUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val fetchUserInfoUseCase: FetchUserInfoUseCase,
    private val deleteMessagingTokenUseCase: DeleteMessagingTokenUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getActiveReservationUseCase: GetActiveReservationUseCase,
    private val finishParkingUseCase: FinishReservationUseCase
) : BaseViewModel<ProfileState, ProfileEffect, ProfileEvent>(ProfileState()) {

    init {
        fetchUserInfo()
    }

    override fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.BackButtonClicked -> navigateBack()
            is ProfileEvent.ChangePasswordButtonClicked -> navigateToChangePassword()
            is ProfileEvent.SignOutButtonClicked -> checkActiveReservation()
            is ProfileEvent.DismissActiveReservationDialog -> dismissActiveReservationDialog()
            is ProfileEvent.FinishParking -> finishAndSignOut()
            is ProfileEvent.Refresh -> refresh()
        }
    }

    private fun refresh() {
        fetchUserInfo()
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
                                error = null,
                                userBalance = userInfo.parkingBalance
                            )
                        }
                    }

                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState { copy(error = error) }
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
            val removeTokenResponse = deleteMessagingTokenUseCase()
            if (removeTokenResponse is Resource.Success) {
                signOutUseCase().collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }
                    when (resource) {
                        is Resource.Success -> Unit

                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(ProfileEffect.Error(error))
                        }

                        is Resource.Loading -> Unit
                    }
                }
            } else if (removeTokenResponse is Resource.Error) {
                val error = removeTokenResponse.error.toGenericString()
                sendSideEffect(ProfileEffect.Error(error))
            }
        }
    }

    private fun dismissActiveReservationDialog() {
        updateState { copy(showActiveReservationDialog = false) }
    }

    private fun checkActiveReservation() {

        viewModelScope.launch {
            getActiveReservationUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }

                when (resource) {

                    is Resource.Success -> {
                        val activeReservation = resource.data
                        if (activeReservation != null) {
                            updateState { copy(activeReservationId = activeReservation.id) }
                            updateState { copy(showActiveReservationDialog = true) }
                        } else {
                            signOut()
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

    private fun finishAndSignOut() {
        viewModelScope.launch {
            val activeReservationId = state.activeReservationId
            if (activeReservationId != null) {
                finishParkingUseCase(FinishReservationRequest(activeReservationId)).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }
                    when (resource) {
                        is Resource.Success -> {
                            signOut()
                        }

                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(ProfileEffect.Error(error))
                        }

                        is Resource.Loading -> Unit
                    }

                }

            } else {
                signOut()
            }

        }
    }
}