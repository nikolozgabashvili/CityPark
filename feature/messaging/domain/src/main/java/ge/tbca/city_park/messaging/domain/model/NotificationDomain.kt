package ge.tbca.city_park.messaging.domain.model

data class NotificationDomain(
    val id: Int,
    val userId: Int,
    val title: String,
    val message: String,
    val createdAtMillis: Long
)