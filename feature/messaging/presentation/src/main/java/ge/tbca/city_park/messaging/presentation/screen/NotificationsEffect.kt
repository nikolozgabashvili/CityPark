package ge.tbca.city_park.messaging.presentation.screen

import ge.tbca.city_park.core.ui.util.GenericString

sealed interface NotificationsEffect {
    data class Error(val error: GenericString) : NotificationsEffect
    data object NavigateBack: NotificationsEffect
}