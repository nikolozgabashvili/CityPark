package ge.tbca.city_park.reservation.data.mapper

import ge.tbca.city_park.reservation.data.model.ReservationRequestDTO
import ge.tbca.city_park.reservation.domain.model.ReservationRequest

fun ReservationRequest.toDTO(): ReservationRequestDTO {
    return ReservationRequestDTO(
        zoneCode = zoneCode,
        carNumber = carNumber
    )
}