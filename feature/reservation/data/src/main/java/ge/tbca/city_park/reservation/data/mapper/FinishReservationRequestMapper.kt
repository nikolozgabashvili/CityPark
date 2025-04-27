package ge.tbca.city_park.reservation.data.mapper

import ge.tbca.city_park.reservation.data.model.FinishReservationRequestDTO
import ge.tbca.city_park.reservation.domain.model.FinishReservationRequest

fun FinishReservationRequest.toDTO(): FinishReservationRequestDTO {
    return FinishReservationRequestDTO(
        this.reservationId
    )
}