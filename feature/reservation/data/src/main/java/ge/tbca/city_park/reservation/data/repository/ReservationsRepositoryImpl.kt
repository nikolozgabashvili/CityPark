package ge.tbca.city_park.reservation.data.repository

import ge.tbca.city_park.core.data.extension.mapResource
import ge.tbca.city_park.core.data.extension.toEmptyResource
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.reservation.data.mapper.toDTO
import ge.tbca.city_park.reservation.data.mapper.toDomain
import ge.tbca.city_park.reservation.data.service.ReservationApiService
import ge.tbca.city_park.reservation.domain.model.FinishReservationRequest
import ge.tbca.city_park.reservation.domain.model.FinishReservationResponse
import ge.tbca.city_park.reservation.domain.model.ReservationDomain
import ge.tbca.city_park.reservation.domain.model.ReservationRequest
import ge.tbca.city_park.reservation.domain.repository.ReservationsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReservationsRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val apiService: ReservationApiService
) : ReservationsRepository {

    override fun getAllReservations(): Flow<Resource<List<ReservationDomain>, ApiError>> {
        return apiHelper.safeCall {
            apiService.getReservationsHistory()
        }.mapResource {
            it.toDomain()
        }
    }

    override fun createReservation(reservation: ReservationRequest): Flow<Resource<Unit, ApiError>> {
        return apiHelper.safeCall {
            apiService.createReservation(reservation.toDTO())
        }.toEmptyResource()
    }

    override fun finishReservation(reservation: FinishReservationRequest): Flow<Resource<FinishReservationResponse, ApiError>> {
        return apiHelper.safeCall {
            apiService.finishReservation(reservation.toDTO())
        }.mapResource {
            it.toDomain()
        }
    }
}