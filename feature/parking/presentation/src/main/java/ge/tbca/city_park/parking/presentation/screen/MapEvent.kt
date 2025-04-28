package ge.tbca.city_park.parking.presentation.screen

import com.google.android.gms.maps.model.LatLng

sealed interface MapEvent {
    data object MapLoaded : MapEvent
    data object DismissBottomSheet : MapEvent
    data object ShowCarBottomSheet : MapEvent
    data object HideCarBottomSheet : MapEvent
    data object NavigateToAddCar : MapEvent
    data object NavigateToAddBalance : MapEvent
    data object DismissAlertDialog : MapEvent
    data object StartParking : MapEvent
    data object DismissPermissionDialog : MapEvent

    data class OnParkingSpotClick(val id: Int) : MapEvent
    data class OnCarSelected(val id: Int) : MapEvent
    data class OnClusterClick(val location: LatLng) : MapEvent
    data class OnPermissionChanged(val isGranted: Boolean) : MapEvent
}