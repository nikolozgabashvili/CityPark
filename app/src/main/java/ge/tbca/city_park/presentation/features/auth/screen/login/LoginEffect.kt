package ge.tbca.city_park.presentation.features.auth.screen.login

import ge.tbca.city_park.presentation.core.util.GenericString

sealed interface LoginEffect {
    data object NavigateToRegister : LoginEffect
    data object NavigateToForgotPassword : LoginEffect
    data object Success : LoginEffect
    data class Error(val error: GenericString) : LoginEffect
}