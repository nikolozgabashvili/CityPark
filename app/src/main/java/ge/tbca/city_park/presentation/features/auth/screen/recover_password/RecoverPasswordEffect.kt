package ge.tbca.city_park.presentation.features.auth.screen.recover_password

import ge.tbca.city_park.presentation.core.util.GenericString

sealed interface RecoverPasswordEffect {
    data object NavigateBack : RecoverPasswordEffect
    data object Success : RecoverPasswordEffect
    data class Error(val error: GenericString) : RecoverPasswordEffect
}