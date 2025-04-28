package ge.tbca.city_park.parking.presentation.model

import com.google.android.gms.maps.model.LatLng

data class ParkingSpot(
    val id: Int,
    val name: String,
    val address: String,
    val location: LatLng,
    val country: String,
    val city: String,
    val district: String,
    val parkingSpotAmount: Int,
    val availableSpots: Int,
    val zoneCode: String,
)