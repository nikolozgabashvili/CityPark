package ge.tbca.city_park.fines.data.repository

import ge.tbca.city_park.core.data.extension.mapResource
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.fines.data.mapper.toDTO
import ge.tbca.city_park.fines.data.mapper.toDomain
import ge.tbca.city_park.fines.data.service.FineApiService
import ge.tbca.city_park.fines.domain.model.ParkingFineDomain
import ge.tbca.city_park.fines.domain.model.PayFineRequest
import ge.tbca.city_park.fines.domain.repository.FinesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FineRepositoryIml @Inject constructor(
    private val apiHelper: ApiHelper,
    private val fineApiService: FineApiService
) : FinesRepository {
    override fun getParkingFines(): Flow<Resource<List<ParkingFineDomain>, ApiError>> {
        return apiHelper.safeCall { fineApiService.getFines() }.mapResource { it.toDomain() }
    }

    override fun payFine(payFineRequest: PayFineRequest): Flow<Resource<Unit, ApiError>> {
        return apiHelper.safeCall { fineApiService.payFines(payFineRequest.toDTO()) }
    }

    override fun getFineById(fineId: Int): Flow<Resource<ParkingFineDomain, ApiError>> {
        return apiHelper.safeCall { fineApiService.getFinesById(fineId) }
            .mapResource { it.toDomain() }
    }

}