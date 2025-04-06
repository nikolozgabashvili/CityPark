package ge.tbca.city_park.presentation.features.login

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.domain.core.usecase.ValidateEmailUseCase
import ge.tbca.city_park.domain.core.usecase.ValidateFieldUseCase
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val validateFieldUseCase: ValidateFieldUseCase,
    private val validateEmailUseCase: ValidateEmailUseCase,
) :
    BaseViewModel<LoginState, LoginEffect, LoginEvent>(LoginState()) {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> updateEmail(event.email)
            is LoginEvent.ForgotPasswordClicked -> navigateToForgotScreen()
            is LoginEvent.LoginButtonClicked -> login()
            is LoginEvent.GoogleLoginButtonClicked -> loginWithGoogle()
            is LoginEvent.PasswordChanged -> updatePassword(event.password)
            is LoginEvent.PasswordVisibilityChanged -> updatePasswordVisibility()
            is LoginEvent.RegisterHereClicked -> navigateToRegister()
        }
    }

    private fun loginWithGoogle() {
        //todo
    }

    private fun navigateToRegister() {
        viewModelScope.launch {
            sendSideEffect(LoginEffect.NavigateToRegister)
        }
    }

    private fun navigateToForgotScreen() {
        viewModelScope.launch {
            sendSideEffect(LoginEffect.NavigateToForgotPassword)
        }
    }

    private fun login() {
        val isEmailValid = validateEmailUseCase(state.email)
        val isPasswordValid = validateFieldUseCase(state.password)
        if (isEmailValid && isPasswordValid) {
            //todo login user
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