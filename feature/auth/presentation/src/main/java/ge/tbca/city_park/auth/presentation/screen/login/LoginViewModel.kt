package ge.tbca.city_park.auth.presentation.screen.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.auth.domain.usecase.LoginUseCase
import ge.tbca.city_park.auth.domain.usecase.ValidateEmailUseCase
import ge.tbca.city_park.auth.domain.usecase.ValidateFieldUseCase
import ge.tbca.city_park.auth.presentation.extension.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateFieldUseCase: ValidateFieldUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginState, LoginEffect, LoginEvent>(LoginState()) {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> updateEmail(event.email)
            is LoginEvent.PasswordRecoveryClicked -> navigateToRecoveryScreen()
            is LoginEvent.LoginButtonClicked -> login()
            is LoginEvent.GoogleLoginButtonClicked -> loginWithGoogle()
            is LoginEvent.PasswordChanged -> updatePassword(event.password)
            is LoginEvent.PasswordVisibilityChanged -> updatePasswordVisibility()
            is LoginEvent.RegisterHereClicked -> navigateToRegister()
        }
    }

    private fun loginWithGoogle() {
        //todo probably remove this
    }

    private fun navigateToRegister() {
        viewModelScope.launch {
            sendSideEffect(LoginEffect.NavigateToRegister)
        }
    }

    private fun navigateToRecoveryScreen() {
        viewModelScope.launch {
            sendSideEffect(LoginEffect.NavigateToPasswordRecovery)
        }
    }

    private fun login() {
        val isEmailValid = validateEmailUseCase(state.email)
        val isPasswordValid = validateFieldUseCase(state.password)
        if (isEmailValid && isPasswordValid) {
            viewModelScope.launch {
                loginUseCase(state.email, state.password).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }
                    when (resource) {
                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(LoginEffect.Error(error))
                        }

                        is Resource.Success -> {
                            sendSideEffect(LoginEffect.Success)
                        }
                        Resource.Loading -> Unit
                    }
                }
            }
        } else {
            updateState {
                copy(
                    showEmailError = !isEmailValid,
                    showPasswordError = !isPasswordValid
                )
            }
        }

    }

    private fun updatePasswordVisibility() {
        updateState { copy(isPasswordVisible = !isPasswordVisible) }
    }

    private fun updatePassword(password: String) {
        updateState {
            copy(password = password, showPasswordError = false)
        }
    }

    private fun updateEmail(email: String) {
        updateState {
            copy(email = email, showEmailError = false)
        }

    }
}