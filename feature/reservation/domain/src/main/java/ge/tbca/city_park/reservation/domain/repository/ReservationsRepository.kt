package ge.tbca.city_park.reservation.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.reservation.domain.model.FinishReservationRequest
import ge.tbca.city_park.reservation.domain.model.ReservationDomain
import ge.tbca.city_park.reservation.domain.model.ReservationRequest
import kotlinx.coroutines.flow.Flow

interface ReservationsRepository {
    fun getAllReservations(): Flow<Resource<List<ReservationDomain>, ApiError>>
    fun getActiveReservation(): Flow<Resource<ReservationDomain?, ApiError>>
    fun createReservation(reservation: ReservationRequest): Flow<Resource<Unit, ApiError>>
    fun finishReservation(reservation: FinishReservationRequest): Flow<Resource<Unit, ApiError>>
}