package ge.tbca.city_park.parking.presentation.screen

import com.google.android.gms.maps.model.LatLng
import ge.tbca.city_park.cars.presentation.model.CarUi
import ge.tbca.city_park.parking.presentation.model.ParkingClusterUi
import ge.tbca.city_park.parking.presentation.model.ParkingSpot

data class MapState(
    val carsLoading: Boolean = true,
    val parkingSpotsLoading: Boolean = true,
    val mapLoaded: Boolean = false,
    val parkingSpots: List<ParkingSpot> = emptyList(),
    val cars: List<CarUi> = emptyList(),
    val parkingClusters: List<ParkingClusterUi> = emptyList(),
    val zoomToLocation: LatLng? = null,
    val selectedCarId: Int? = null,
    val selectedParkingSpotId: Int? = null,
    val reservationLoading: Boolean = false,
    val canShowLocationPermissionDialog: Boolean = true,
    val locationPermissionGranted: Boolean = false,
    val showInsufficientBalanceDialog: Boolean = false,
    val isOnline: Boolean = true,

    val showCarBottomSheet: Boolean = false,
) {
    val selectedParkingSpot: ParkingSpot?
        get() = parkingSpots.find { it.id == selectedParkingSpotId }

    val selectedCar: CarUi?
        get() = cars.find { it.id == selectedCarId }

    val isLoading: Boolean
        get() = carsLoading || parkingSpotsLoading

    val canStartReservation: Boolean
        get() = selectedCar != null && selectedParkingSpot != null && selectedParkingSpot?.availableSpots != 0

}