package ge.tbca.city_park.user.presentation.screen

import ge.tbca.city_park.core.ui.util.GenericString

sealed interface ProfileEffect {
    data class Error(val error: GenericString) : ProfileEffect
    data object NavigateBack : ProfileEffect
    data object NavigateToChangePassword : ProfileEffect
}