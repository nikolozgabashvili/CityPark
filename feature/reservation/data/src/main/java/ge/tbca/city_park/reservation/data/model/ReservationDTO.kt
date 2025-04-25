package ge.tbca.city_park.reservation.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ReservationDTO(
    val id: Int,
    val userId: String,
    val parkingSpotId: Int,
    val zoneCode: String,
    val carNumber: String,
    val createdAt: Long,
    val active: Boolean = true,
    val endedAt: Long? = null,
    val cost: Long? = null,
    val duration: Long? = null
)