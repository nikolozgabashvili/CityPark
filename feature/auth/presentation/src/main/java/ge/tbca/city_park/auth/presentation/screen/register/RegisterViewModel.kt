package ge.tbca.city_park.auth.presentation.screen.register

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.auth.domain.usecase.SignUpUseCase
import ge.tbca.city_park.auth.domain.usecase.ValidateEmailUseCase
import ge.tbca.city_park.auth.domain.usecase.ValidatePasswordUseCase
import ge.tbca.city_park.auth.presentation.extension.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val signUpUseCase: SignUpUseCase
) : BaseViewModel<RegisterState, RegisterEffect, RegisterEvent>(
    RegisterState()
    ) {

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

            viewModelScope.launch {
                signUpUseCase(
                    email = state.email,
                    password = state.password
                ).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }
                    when (resource) {
                        is Resource.Success -> sendSideEffect(RegisterEffect.NavigateToHome)
                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(RegisterEffect.Error(error))
                        }

                        Resource.Loading -> Unit
                    }
                }
            }

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
        //todo probably remove this

    }

}