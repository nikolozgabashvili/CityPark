package ge.tbca.city_park.cars.domain.repository

import ge.tbca.city_park.cars.domain.model.AddCarRequestDomain
import ge.tbca.city_park.cars.domain.model.CarDomain
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CarsRepository {
    fun addCar(car: AddCarRequestDomain): Flow<Resource<CarDomain, ApiError>>
    fun getAllCars(): Flow<Resource<List<CarDomain>, ApiError>>
    fun deleteCarById(id: Int): Flow<Resource<Unit, ApiError>>
}