package ge.tbca.city_park.fines.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.fines.domain.model.ParkingFineDomain
import ge.tbca.city_park.fines.domain.model.PayFineRequest
import kotlinx.coroutines.flow.Flow

interface FinesRepository {
    fun getParkingFines(): Flow<Resource<List<ParkingFineDomain>, ApiError>>
    fun payFine(payFineRequest: PayFineRequest): Flow<Resource<Unit, ApiError>>
}