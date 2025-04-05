package ge.tbca.city_park.presentation.features.login

import ge.tbca.city_park.presentation.core.base.BaseViewModel

class LoginViewModel : BaseViewModel<LoginState, LoginEffect, LoginEvent>(LoginState()) {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {}
            is LoginEvent.ForgotPasswordClicked -> {}
            is LoginEvent.LoginButtonClicked -> {}
            is LoginEvent.PasswordChanged -> {}
            is LoginEvent.PasswordVisibilityChanged -> {}
            is LoginEvent.RegisterHereClicked -> {}
        }
    }
}