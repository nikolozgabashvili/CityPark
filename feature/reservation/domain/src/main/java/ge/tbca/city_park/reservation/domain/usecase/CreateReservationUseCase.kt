package ge.tbca.city_park.reservation.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.reservation.domain.model.ReservationRequest
import ge.tbca.city_park.reservation.domain.repository.ReservationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CreateReservationUseCase @Inject constructor(private val reservationsRepository: ReservationsRepository) {

    operator fun invoke(reservation: ReservationRequest): Flow<Resource<Unit, ApiError>> {
        return reservationsRepository.createReservation(reservation)
    }
}