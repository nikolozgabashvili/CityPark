package ge.tbca.city_park.parking.presentation.screen

import com.google.android.gms.maps.model.LatLng
import ge.tbca.city_park.core.ui.util.GenericString

sealed interface MapEffect {
    data class Error(val error: GenericString) : MapEffect
    data class ZoomToLocation(val location: LatLng) : MapEffect

    data object NavigateToAddBalance : MapEffect
    data object NavigateToAddCar : MapEffect
    data object ReservationCreated : MapEffect
}