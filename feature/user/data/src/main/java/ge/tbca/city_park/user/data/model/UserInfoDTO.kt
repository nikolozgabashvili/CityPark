package ge.tbca.city_park.user.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserInfoDTO(
    val id: Int,
    val userId: String,
    val email: String,
    val parkingBalance: Double,
    val fcmToken: String?
)