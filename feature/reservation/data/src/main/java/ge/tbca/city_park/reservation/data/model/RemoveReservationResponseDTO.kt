package ge.tbca.city_park.reservation.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoveReservationResponseDTO(
    val cost: Long,
    val duration: Long
)