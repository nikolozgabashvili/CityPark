package ge.tbca.city_park.fines.data.mapper

import ge.tbca.city_park.fines.data.model.ParkingFineDTO
import ge.tbca.city_park.fines.domain.model.ParkingFineDomain

fun ParkingFineDTO.toDomain(): ParkingFineDomain {
    return ParkingFineDomain(
        id = id,
        userId = userId,
        location = location,
        address = address,
        carNumber = carNumber,
        price = price,
        description = description,
        createdAt = createdAt,
        isPaid = isPaid
    )
}


fun List<ParkingFineDTO>.toDomain(): List<ParkingFineDomain> {
    return map { it.toDomain() }
}