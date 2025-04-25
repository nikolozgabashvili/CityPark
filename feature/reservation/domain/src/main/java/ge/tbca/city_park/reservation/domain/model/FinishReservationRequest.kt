package ge.tbca.city_park.reservation.domain.model

data class FinishReservationRequest(
    val zoneCode: String,
    val carNumber: String
)