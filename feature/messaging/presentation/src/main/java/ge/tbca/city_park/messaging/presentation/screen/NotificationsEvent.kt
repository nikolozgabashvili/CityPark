package ge.tbca.city_park.messaging.presentation.screen

sealed interface NotificationsEvent {
    data class DeleteNotificationClicked(val id: Int) : NotificationsEvent
    data object DismissDeleteAllNotificationsDialog : NotificationsEvent
    data object BackButtonClicked : NotificationsEvent
    data object DeleteAllNotificationsClicked : NotificationsEvent
    data object DeleteAllNotifications : NotificationsEvent
    data object Refresh : NotificationsEvent
}