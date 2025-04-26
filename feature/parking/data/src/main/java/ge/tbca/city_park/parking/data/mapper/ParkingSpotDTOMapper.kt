package ge.tbca.city_park.parking.data.mapper

import ge.tbca.city_park.parking.data.model.ParkingSpotDTO
import ge.tbca.city_park.parking.domain.model.ParkingSpotDomain

fun ParkingSpotDTO.toDomain(): ParkingSpotDomain{
    return ParkingSpotDomain(
        id = id,
        name = name,
        address = address,
        latitude = latitude,
        longitude = longitude,
        country = country,
        city = city,
        district = district,
        parkingSpotAmount = parkingSpotAmount,
        availableSpots = availableSpots,
        zoneCode = zoneCode
    )
}

fun List<ParkingSpotDTO>.toDomain(): List<ParkingSpotDomain> {
    return map { it.toDomain() }
}