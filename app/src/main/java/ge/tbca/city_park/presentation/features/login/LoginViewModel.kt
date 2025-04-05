package ge.tbca.city_park.presentation.features.login

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :
    BaseViewModel<LoginState, LoginEffect, LoginEvent>(LoginState()) {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> updateEmail(event.email)

            is LoginEvent.ForgotPasswordClicked -> {}
            is LoginEvent.LoginButtonClicked -> {}
            is LoginEvent.GoogleLoginButtonClicked -> {}
            is LoginEvent.PasswordChanged -> updatePassword(event.password)
            is LoginEvent.PasswordVisibilityChanged -> updatePasswordVisibility()
            is LoginEvent.RegisterHereClicked -> {}
        }
    }

    private fun updatePasswordVisibility() {
        updateState { copy(isPasswordVisible = !isPasswordVisible) }
    }

    private fun updatePassword(password: String) {
        updateState {
            copy(password = password)
        }
    }

    private fun updateEmail(email: String) {
        updateState {
            copy(email = email)
        }

    }
}