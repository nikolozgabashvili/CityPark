package ge.tbca.city_park.cars.data.repository

import ge.tbca.city_park.cars.domain.error.CarError
import ge.tbca.city_park.cars.domain.model.CarDomain
import ge.tbca.city_park.cars.domain.repository.CarsRepository
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FakeCarsRepositoryImpl @Inject constructor() : CarsRepository {

    override fun addCar(car: CarDomain): Flow<Resource<CarDomain, CarError>> {
        TODO()
    }

    override fun getAllCars(): Flow<Resource<List<CarDomain>, CarError>> {
        TODO()
    }

    override fun deleteCarById(id: Int): Flow<Resource<Boolean, CarError>> {
        TODO()
    }
}