package ge.tbca.city_park.reservation.domain.model

data class RemoveReservationRequest(
    val zoneCode: String,
    val carNumber: String
)