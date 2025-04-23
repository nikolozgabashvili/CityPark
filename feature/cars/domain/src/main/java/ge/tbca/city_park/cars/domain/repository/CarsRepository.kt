package ge.tbca.city_park.cars.domain.repository

import ge.tbca.city_park.cars.domain.error.CarError
import ge.tbca.city_park.cars.domain.model.Car
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CarsRepository {
    fun addCar(car: Car): Flow<Resource<Unit, CarError>>
    fun getAllCars(): Flow<Resource<List<Car>, CarError>>
    fun deleteCarById(id: String): Flow<Resource<Unit, CarError>>
}