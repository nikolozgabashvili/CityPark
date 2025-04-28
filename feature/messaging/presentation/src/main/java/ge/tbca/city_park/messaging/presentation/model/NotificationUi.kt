package ge.tbca.city_park.messaging.presentation.model

data class NotificationUi(
    val id: Int,
    val userId: Int,
    val title: String,
    val message: String,
    val createdAt: String
)