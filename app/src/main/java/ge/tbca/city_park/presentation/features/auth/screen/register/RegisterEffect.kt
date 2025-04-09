package ge.tbca.city_park.presentation.features.auth.screen.register

import ge.tbca.city_park.presentation.core.util.GenericString

sealed interface RegisterEffect {
    data object NavigateBack : RegisterEffect
    data object NavigateToHome : RegisterEffect
    data class Error(val error: GenericString) : RegisterEffect
}