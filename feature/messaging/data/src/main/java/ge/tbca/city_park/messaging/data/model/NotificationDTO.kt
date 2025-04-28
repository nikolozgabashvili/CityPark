package ge.tbca.city_park.messaging.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NotificationDTO(
    val id: Int,
    val userId: Int,
    val title: String,
    val message: String,
    val createdAtMillis: Long
)