package ge.tbca.city_park.reservation.data.mapper

import ge.tbca.city_park.reservation.data.model.FinishReservationResponseDTO
import ge.tbca.city_park.reservation.domain.model.FinishReservationResponse

fun FinishReservationResponseDTO.toDomain(): FinishReservationResponse {
    return FinishReservationResponse(
        cost = cost,
        duration = duration
    )
}