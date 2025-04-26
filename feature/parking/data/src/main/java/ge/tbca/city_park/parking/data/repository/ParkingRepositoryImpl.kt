package ge.tbca.city_park.parking.data.repository

import ge.tbca.city_park.core.data.extension.mapResource
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.parking.data.mapper.toDomain
import ge.tbca.city_park.parking.data.service.ParkingApiService
import ge.tbca.city_park.parking.domain.model.ParkingSpotDomain
import ge.tbca.city_park.parking.domain.repository.ParkingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ParkingRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val parkingApiService: ParkingApiService
) : ParkingRepository {

    override fun getParkingSpots(): Flow<Resource<List<ParkingSpotDomain>, ApiError>> {
        return apiHelper.safeCall {
            parkingApiService.getParkingSpots()
        }.mapResource {
            it.toDomain()
        }
    }

    override fun getParkingSpotByZoneCode(zoneCode: String): Flow<Resource<ParkingSpotDomain, ApiError>> {
        return apiHelper.safeCall {
            parkingApiService.getParkingSpotByZoneCode(zoneCode)
        }.mapResource {
            it.toDomain()
        }
    }
}