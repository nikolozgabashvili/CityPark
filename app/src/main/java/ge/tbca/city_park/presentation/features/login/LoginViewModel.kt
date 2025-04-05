package ge.tbca.city_park.presentation.features.login

import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.presentation.core.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() :
    BaseViewModel<LoginState, LoginEffect, LoginEvent>(LoginState()) {

    override fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {}
            is LoginEvent.ForgotPasswordClicked -> {}
            is LoginEvent.LoginButtonClicked -> {}
            is LoginEvent.GoogleLoginButtonClicked -> {}
            is LoginEvent.PasswordChanged -> {}
            is LoginEvent.PasswordVisibilityChanged -> {}
            is LoginEvent.RegisterHereClicked -> {}
        }
    }
}