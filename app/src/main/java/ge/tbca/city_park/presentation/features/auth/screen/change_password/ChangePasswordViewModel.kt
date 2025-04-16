package ge.tbca.city_park.presentation.features.auth.screen.change_password

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.ValidatePasswordUseCase
import ge.tbca.city_park.domain.core.util.Resource
import ge.tbca.city_park.domain.core.util.isLoading
import ge.tbca.city_park.domain.features.auth.usecase.ChangePasswordUseCase
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import ge.tbca.city_park.presentation.core.extension.toGenericString
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase
) : BaseViewModel<ChangePasswordState, ChangePasswordEffect, ChangePasswordEvent>(
    ChangePasswordState()
) {

    override fun onEvent(event: ChangePasswordEvent) {
        when (event) {
            is ChangePasswordEvent.NavigateBack -> navigateBack()
            is ChangePasswordEvent.ChangePasswordButtonClicked -> changePassword()
            is ChangePasswordEvent.NewPasswordChanged -> updateNewPassword(event.newPassword)
            is ChangePasswordEvent.NewPasswordVisibilityChanged -> updateNewPasswordVisibility()
            is ChangePasswordEvent.OldPasswordChanged -> updateOldPassword(event.oldPassword)
            is ChangePasswordEvent.OldPasswordVisibilityChanged -> updateOldPasswordVisibility()
            is ChangePasswordEvent.RepeatNewPasswordChanged -> updateRepeatNewPassword(event.repeatNewPassword)
            is ChangePasswordEvent.RepeatNewPasswordVisibilityChanged -> updateRepeatNewPasswordVisibility()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(ChangePasswordEffect.NavigateBack)
        }
    }

    private fun changePassword() {
        val isNewPasswordValid = state.passwordValidationState.isValid
        val passwordsMatch = state.newPassword == state.repeatNewPassword

        if (isNewPasswordValid && passwordsMatch) {

            viewModelScope.launch {
                changePasswordUseCase(state.oldPassword,state.newPassword).collect{resource ->
                    updateState { copy(isLoading = resource.isLoading() ) }
                    when(resource){
                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(ChangePasswordEffect.Error(error))
                        }
                        is Resource.Success -> {
                            sendSideEffect(ChangePasswordEffect.Success)
                        }
                        Resource.Loading -> Unit
                    }
                }
            }

        } else {
            updateState {
                copy(
                    showNewPasswordError = !isNewPasswordValid,
                    showRepeatPasswordError = !passwordsMatch
                )
            }
        }
    }

    private fun updateNewPassword(newPassword: String) {
        updateState {
            copy(
                newPassword = newPassword,
                passwordValidationState = validatePasswordUseCase(newPassword),
                showNewPasswordError = false
            )
        }
    }

    private fun updateNewPasswordVisibility() {
        updateState {
            copy(isNewPasswordVisible = !isNewPasswordVisible)
        }
    }

    private fun updateOldPassword(oldPassword: String) {
        updateState {
            copy(
                oldPassword = oldPassword
            )
        }
    }

    private fun updateOldPasswordVisibility() {
        updateState {
            copy(isOldPasswordVisible = !isOldPasswordVisible)
        }
    }

    private fun updateRepeatNewPassword(repeatNewPassword: String) {
        updateState {
            copy(
                repeatNewPassword = repeatNewPassword,
                showRepeatPasswordError = false
            )
        }
    }

    private fun updateRepeatNewPasswordVisibility() {
        updateState {
            copy(isRepeatNewPasswordVisible = !isRepeatNewPasswordVisible)
        }
    }
}