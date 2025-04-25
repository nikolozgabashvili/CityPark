package ge.tbca.city_park.reservation.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.reservation.domain.model.RemoveReservationRequest
import ge.tbca.city_park.reservation.domain.model.RemoveReservationResponse
import ge.tbca.city_park.reservation.domain.repository.ReservationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoveReservationUseCase @Inject constructor(private val reservationsRepository: ReservationsRepository) {

    operator fun invoke(reservation: RemoveReservationRequest): Flow<Resource<RemoveReservationResponse, ApiError>> {
        return reservationsRepository.removeReservation(reservation)
    }
}