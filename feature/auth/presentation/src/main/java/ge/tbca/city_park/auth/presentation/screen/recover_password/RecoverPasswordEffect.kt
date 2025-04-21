package ge.tbca.city_park.auth.presentation.screen.recover_password

import ge.tbca.citi_park.core.ui.util.GenericString


sealed interface RecoverPasswordEffect {
    data object NavigateBack : RecoverPasswordEffect
    data object Success : RecoverPasswordEffect
    data class Error(val error: GenericString) : RecoverPasswordEffect
}