package ge.tbca.city_park.cars.data.repository

import ge.tbca.city_park.cars.domain.error.CarError
import ge.tbca.city_park.cars.domain.model.Car
import ge.tbca.city_park.cars.domain.repository.CarsRepository
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeCarsRepositoryImpl @Inject constructor() : CarsRepository {

    override fun addCar(car: Car): Flow<Resource<Unit, CarError>> = flow {
        emit(Resource.Loading)
        delay(2000L)
        emit(Resource.Success(Unit))
    }

    override fun getAllCars(): Flow<Resource<List<Car>, CarError>> = flow {
        emit(Resource.Loading)
        delay(2000L)
        emit(Resource.Success(emptyList()))
    }

    override fun deleteCarById(id: String): Flow<Resource<Unit, CarError>> = flow {
        emit(Resource.Loading)
        delay(2000L)
        emit(Resource.Success(Unit))
    }
}