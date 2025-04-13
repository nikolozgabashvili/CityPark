package ge.tbca.city_park.presentation.features.change_password.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.ValidatePasswordUseCase
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val validatePasswordUseCase: ValidatePasswordUseCase
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
        val isOldPasswordValid = true // TODO check old password
        val isNewPasswordValid = state.passwordValidationState.isValid
        val passwordsMatch = state.newPassword == state.repeatNewPassword

        if (isOldPasswordValid && isNewPasswordValid && passwordsMatch) {
            // TODO change password
        } else {
            updateState {
                copy(
                    showOldPasswordError = !isOldPasswordValid,
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
                oldPassword = oldPassword,
                showOldPasswordError = false
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