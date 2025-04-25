package ge.tba.city_park.reservation.presentation.model

data class ReservationUi(
    val id: Int,
    val parkingSpotId: Int,
    val zoneCode: String,
    val carNumber: String,
    val createdAt: String,
    val active: Boolean = true,
    val endedAt: String? = null,
    val cost: Long? = null
)
