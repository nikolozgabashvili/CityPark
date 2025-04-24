package ge.tbca.city_park.cars.data.repository

import ge.tbca.city_park.cars.data.mapper.toDTO
import ge.tbca.city_park.cars.data.mapper.toDomain
import ge.tbca.city_park.cars.data.service.CarApiService
import ge.tbca.city_park.cars.domain.model.AddCarRequestDomain
import ge.tbca.city_park.cars.domain.model.CarDomain
import ge.tbca.city_park.cars.domain.repository.CarsRepository
import ge.tbca.city_park.core.data.extension.mapResource
import ge.tbca.city_park.core.data.helper.ApiHelper
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarsRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val carApiService: CarApiService
) : CarsRepository {

    override fun addCar(car: AddCarRequestDomain): Flow<Resource<CarDomain, ApiError>> {
        return apiHelper.safeCall {
            carApiService.addCar(car.toDTO())
        }.mapResource { dto ->
            dto.toDomain()
        }
    }

    override fun getAllCars(): Flow<Resource<List<CarDomain>, ApiError>> {
        return apiHelper.safeCall {
            carApiService.getCars()
        }.mapResource {
            it.toDomain()
        }
    }

    override fun deleteCarById(id: Int): Flow<Resource<Unit, ApiError>> {
        return apiHelper.safeCall {
            carApiService.deleteCar(id)
        }.mapResource {}

    }
}