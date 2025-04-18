package ge.tbca.city_park.presentation.features.auth.screen.change_password

import ge.tbca.city_park.presentation.core.util.GenericString

sealed interface ChangePasswordEffect {
    data object NavigateBack : ChangePasswordEffect
    data object Success : ChangePasswordEffect
    data class Error(val error:GenericString) : ChangePasswordEffect
}