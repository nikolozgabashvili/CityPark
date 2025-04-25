package ge.tbca.city_park.reservation.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.reservation.domain.model.FinishReservationRequest
import ge.tbca.city_park.reservation.domain.model.FinishReservationResponse
import ge.tbca.city_park.reservation.domain.repository.ReservationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FinishReservationUseCase @Inject constructor(private val reservationsRepository: ReservationsRepository) {

    operator fun invoke(reservation: FinishReservationRequest): Flow<Resource<FinishReservationResponse, ApiError>> {
        return reservationsRepository.finishReservation(reservation)
    }
}