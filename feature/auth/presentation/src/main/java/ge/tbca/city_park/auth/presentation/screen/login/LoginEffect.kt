package ge.tbca.city_park.auth.presentation.screen.login

import ge.tbca.citi_park.core.ui.util.GenericString


sealed interface LoginEffect {
    data object NavigateToRegister : LoginEffect
    data object NavigateToPasswordRecovery : LoginEffect
    data object Success : LoginEffect

    data class Error(val error: GenericString) : LoginEffect
}