package ge.tbca.city_park.presentation.features.register

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.ValidateEmailUseCase
import ge.tbca.city_park.domain.core.usecase.ValidatePasswordUseCase
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) :
    BaseViewModel<RegisterState, RegisterEffect, RegisterEvent>(RegisterState()) {

    override fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.EmailChanged -> updateEmail(event.email)
            is RegisterEvent.PasswordChanged -> updatePassword(event.password)
            is RegisterEvent.PasswordVisibilityChanged -> updatePasswordVisibility()
            is RegisterEvent.RegisterButtonClicked -> register()
            is RegisterEvent.GoogleButtonClicked -> signUpWithGoogle()
            is RegisterEvent.BackButtonClicked -> navigateBack()
            is RegisterEvent.RepeatPasswordChanged -> updateRepeatPassword(event.repeatPassword)
            is RegisterEvent.RepeatPasswordVisibilityChanged -> updateRepeatPasswordVisibility()
        }
    }



    private fun updatePassword(password: String) {
        updateState {
            copy(
                password = password,
                passwordValidationState = validatePasswordUseCase(password),
                showPasswordError = false
            )
        }
    }

    private fun updateEmail(email: String) {
        updateState {
            copy(
                email = email,
                showEmailError = false
            )
        }
    }

    private fun updatePasswordVisibility() {
        updateState {
            copy(isPasswordVisible = !isPasswordVisible)
        }

    }

    private fun register() {
        val isEmailValid = validateEmailUseCase(state.email)
        val isPasswordValid = state.passwordValidationState.isValid
        val passwordsMatch = state.password == state.repeatPassword

        if (isPasswordValid && isEmailValid && passwordsMatch) {
            //todo register
        } else {
            updateState {
                copy(
                    showEmailError = !isEmailValid,
                    showPasswordError = !isPasswordValid,
                    showRepeatPasswordError = !passwordsMatch
                )
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(RegisterEffect.NavigateBack)
        }
    }

    private fun updateRepeatPassword(repeatPassword: String) {
        updateState {
            copy(
                repeatPassword = repeatPassword,
                showRepeatPasswordError = false
            )
        }
    }

    private fun updateRepeatPasswordVisibility() {
        updateState {
            copy(
                isRepeatPasswordVisible = !isRepeatPasswordVisible
            )
        }
    }

    private fun signUpWithGoogle() {
        viewModelScope.launch {
            sendSideEffect(RegisterEffect.SignUpWithGoogle)
        }

    }

}