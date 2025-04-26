package ge.tbca.city_park.reservation.domain.model

data class ReservationDomain(
    val id: Int,
    val userId: String,
    val parkingSpotId: Int,
    val zoneCode: String,
    val carNumber: String,
    val createdAt: Long,
    val active: Boolean = true,
    val endedAt: Long? = null,
    val cost: Double? = null,
    val duration: Long? = null
)
