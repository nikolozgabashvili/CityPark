package ge.tbca.city_park.auth.presentation.screen.register

import ge.tbca.citi_park.core.ui.util.GenericString


sealed interface RegisterEffect {
    data object NavigateBack : RegisterEffect
    data object Success : RegisterEffect
    data class Error(val error: GenericString) : RegisterEffect
}