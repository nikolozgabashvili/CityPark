package ge.tbca.city_park.reservation.domain.model

data class ReservationRequest(
    val zoneCode: String,
    val carNumber: String
)