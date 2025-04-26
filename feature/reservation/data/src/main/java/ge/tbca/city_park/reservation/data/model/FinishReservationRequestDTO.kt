package ge.tbca.city_park.reservation.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FinishReservationRequestDTO(
    val zoneCode: String,
    val carNumber: String
)