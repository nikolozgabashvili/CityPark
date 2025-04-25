package ge.tbca.city_park.reservation.data.mapper

import ge.tbca.city_park.reservation.data.model.ReservationDTO
import ge.tbca.city_park.reservation.domain.model.ReservationDomain

fun ReservationDTO.toDomain(): ReservationDomain {
    return ReservationDomain(
        id = id,
        userId = userId,
        parkingSpotId = parkingSpotId,
        zoneCode = zoneCode,
        carNumber = carNumber,
        createdAt = createdAt,
        active = active,
        endedAt = endedAt,
        cost = cost,
        duration = duration
    )
}