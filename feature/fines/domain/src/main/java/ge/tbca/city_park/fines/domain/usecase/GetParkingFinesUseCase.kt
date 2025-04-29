package ge.tbca.city_park.fines.domain.usecase

import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.fines.domain.model.ParkingFineDomain
import ge.tbca.city_park.fines.domain.repository.FinesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetParkingFinesUseCase @Inject constructor(private val finesRepository: FinesRepository) {

    operator fun invoke(): Flow<Resource<List<ParkingFineDomain>, ApiError>> {
        return finesRepository.getParkingFines()
    }
}