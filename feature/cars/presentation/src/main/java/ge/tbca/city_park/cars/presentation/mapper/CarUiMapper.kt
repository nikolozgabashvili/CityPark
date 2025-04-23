package ge.tbca.city_park.cars.presentation.mapper

import ge.tbca.city_park.cars.domain.model.Car
import ge.tbca.city_park.cars.presentation.model.CarUi

fun CarUi.toDomain() : Car {
    return Car(
        id = id,
        carName = carName,
        ownerPersonalNumber = ownerPersonalNumber,
        plateNumber = plateNumber
    )
}