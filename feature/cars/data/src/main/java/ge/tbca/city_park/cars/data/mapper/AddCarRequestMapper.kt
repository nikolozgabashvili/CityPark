package ge.tbca.city_park.cars.data.mapper

import ge.tbca.city_park.cars.data.model.AddCarRequest
import ge.tbca.city_park.cars.domain.model.AddCarRequestDomain

fun AddCarRequestDomain.toDTO():AddCarRequest{
    return AddCarRequest(
        name = name,
        carNumber = carNumber,
        ownerPersonalId = ownerPersonalId
    )
}