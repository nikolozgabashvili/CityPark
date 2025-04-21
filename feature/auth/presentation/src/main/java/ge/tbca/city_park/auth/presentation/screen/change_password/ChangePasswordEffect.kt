package ge.tbca.city_park.auth.presentation.screen.change_password

import ge.tbca.citi_park.core.ui.util.GenericString

sealed interface ChangePasswordEffect {
    data object NavigateBack : ChangePasswordEffect
    data object Success : ChangePasswordEffect
    data class Error(val error: GenericString) : ChangePasswordEffect
}