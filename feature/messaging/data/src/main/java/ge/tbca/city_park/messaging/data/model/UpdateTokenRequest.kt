package ge.tbca.city_park.messaging.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UpdateTokenRequest(
    val fcmToken: String?
)
