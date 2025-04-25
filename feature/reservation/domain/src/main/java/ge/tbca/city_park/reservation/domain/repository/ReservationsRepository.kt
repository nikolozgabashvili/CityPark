package ge.tbca.city_park.reservation.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.reservation.domain.model.RemoveReservationRequest
import ge.tbca.city_park.reservation.domain.model.RemoveReservationResponse
import ge.tbca.city_park.reservation.domain.model.ReservationDomain
import ge.tbca.city_park.reservation.domain.model.ReservationRequest
import kotlinx.coroutines.flow.Flow

interface ReservationsRepository {
    fun getAllReservations(): Flow<Resource<List<ReservationDomain>, ApiError>>
    fun createReservation(reservation: ReservationRequest): Flow<Resource<ReservationDomain, ApiError>>
    fun removeReservation(reservation: RemoveReservationRequest): Flow<Resource<RemoveReservationResponse, ApiError>>
}