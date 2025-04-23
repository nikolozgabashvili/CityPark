package ge.tbca.city_park.cars.domain.usecase

import ge.tbca.city_park.cars.domain.error.CarError
import ge.tbca.city_park.cars.domain.repository.CarsRepository
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteCarByIdUseCase @Inject constructor(private val carsRepository: CarsRepository) {

    operator fun invoke(id: String): Flow<Resource<Unit, CarError>> {
        return carsRepository.deleteCarById(id)
    }
}