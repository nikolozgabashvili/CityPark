package ge.tbca.city_park.user.domain.model

data class UserInfoDomain(
    val id: Int,
    val userId: String,
    val email: String,
    val parkingBalance: Double,
    val fcmToken: String? = null
)
