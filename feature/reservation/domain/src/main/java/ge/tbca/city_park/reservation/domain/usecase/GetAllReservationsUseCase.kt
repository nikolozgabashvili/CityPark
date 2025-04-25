package ge.tbca.city_park.reservation.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.reservation.domain.model.ReservationDomain
import ge.tbca.city_park.reservation.domain.repository.ReservationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllReservationsUseCase @Inject constructor(private val reservationsRepository: ReservationsRepository) {

    operator fun invoke(): Flow<Resource<List<ReservationDomain>, ApiError>> {
        return reservationsRepository.getAllReservations()
    }
}