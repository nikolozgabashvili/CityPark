package ge.tbca.city_park.messaging.presentation.screen

import ge.tbca.city_park.core.ui.util.GenericString
import ge.tbca.city_park.messaging.presentation.model.NotificationUi

data class NotificationsState(
    val notificationsList: List<NotificationUi> = emptyList(),
    val noNotifications: Boolean = false,
    val showDeleteAllNotificationsDialog: Boolean = false,
    val error: GenericString? = null,
    val isLoading: Boolean = false
)