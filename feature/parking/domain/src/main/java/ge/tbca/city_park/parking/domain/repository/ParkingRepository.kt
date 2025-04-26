package ge.tbca.city_park.parking.domain.repository

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.parking.domain.model.ParkingSpotDomain
import kotlinx.coroutines.flow.Flow

interface ParkingRepository {
    fun getParkingSpots(): Flow<Resource<List<ParkingSpotDomain>, ApiError>>
    fun getParkingSpotByZoneCode(zoneCode: String): Flow<Resource<ParkingSpotDomain, ApiError>>
}