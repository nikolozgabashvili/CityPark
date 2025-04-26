package ge.tbca.city_park.cars.data.mapper

import ge.tbca.city_park.cars.data.model.CarDTO
import ge.tbca.city_park.cars.domain.model.CarDomain


fun CarDTO.toDomain(): CarDomain {

    return CarDomain(
        id = id,
        carName = name,
        plateNumber = carNumber,
        ownerPersonalNumber = ownerPersonalId
    )

}

fun List<CarDTO>.toDomain(): List<CarDomain> {
    return map { it.toDomain() }
}