package ge.tbca.city_park.parking.presentation.mapper

import com.google.android.gms.maps.model.LatLng
import ge.tbca.city_park.parking.domain.model.ParkingSpotDomain
import ge.tbca.city_park.parking.presentation.model.ParkingSpot

fun ParkingSpotDomain.toPresenter(): ParkingSpot {
    return ParkingSpot(
        id = id,
        name = name,
        address = address,
        location = LatLng(latitude, longitude),
        country = country,
        city = city,
        district = district,
        parkingSpotAmount = parkingSpotAmount,
        availableSpots = availableSpots,
        zoneCode = zoneCode
    )
}

fun List<ParkingSpotDomain>.toPresenter(): List<ParkingSpot> {
    return map { it.toPresenter() }
}