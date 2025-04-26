package ge.tbca.city_park.parking.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.parking.domain.model.ParkingSpotDomain
import ge.tbca.city_park.parking.domain.repository.ParkingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetParkingSpotByZoneCodeUseCase @Inject constructor(private val parkingRepository: ParkingRepository) {

    operator fun invoke(zoneCode: String): Flow<Resource<ParkingSpotDomain, ApiError>> {
        return parkingRepository.getParkingSpotByZoneCode(zoneCode)
    }
}