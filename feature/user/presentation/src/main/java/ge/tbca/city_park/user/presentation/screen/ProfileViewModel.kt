package ge.tbca.city_park.user.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tba.city_park.reservation.presentation.mapper.toPresenter
import ge.tbca.city_park.auth.domain.usecase.SignOutUseCase
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
            is ProfileEvent.FinishParking -> signOut()
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
            val activeReservation = state.activeReservation

            if (activeReservation != null) {
                updateState { copy(showActiveReservationDialog = false) }

                var shouldProceedToSignOut = true
                finishParkingUseCase(FinishReservationRequest(reservationId = activeReservation.id)).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }

                    when (resource) {
                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(ProfileEffect.Error(error))
                            shouldProceedToSignOut = false
                        }

                        is Resource.Success -> Unit
                        is Resource.Loading -> Unit
                    }
                }

                if (!shouldProceedToSignOut) return@launch
            }

            updateState { copy(isLoading = true) }

            when (val request = deleteMessagingTokenUseCase()) {
                is Resource.Success -> {
                    signOutUseCase().collect {
                        updateState { copy(isLoading = false) }
                    }
                }

                is Resource.Error -> {
                    updateState { copy(isLoading = false) }
                    sendSideEffect(ProfileEffect.Error(request.error.toGenericString()))
                }

                is Resource.Loading -> Unit
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
                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState { copy(error = error) }
                    }

                    is Resource.Success -> {
                        val reservation = resource.data?.toPresenter()

                        if (reservation != null) {
                            updateState {
                                copy(
                                    activeReservation = reservation,
                                    showActiveReservationDialog = true
                                )
                            }
                        } else {
                            signOut()
                        }
                    }

                    is Resource.Loading -> Unit
                }

            }
        }
    }
}