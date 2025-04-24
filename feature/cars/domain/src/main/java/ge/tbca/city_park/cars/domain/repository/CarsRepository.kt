package ge.tbca.city_park.cars.domain.repository

import ge.tbca.city_park.cars.domain.error.CarError
import ge.tbca.city_park.cars.domain.model.CarDomain
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface CarsRepository {
    fun addCar(car: CarDomain): Flow<Resource<CarDomain, CarError>>
    fun getAllCars(): Flow<Resource<List<CarDomain>, CarError>>
    fun deleteCarById(id: Int): Flow<Resource<Boolean, CarError>>
}