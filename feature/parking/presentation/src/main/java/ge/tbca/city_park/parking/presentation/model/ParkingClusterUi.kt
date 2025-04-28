package ge.tbca.city_park.parking.presentation.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class ParkingClusterUi(
    val parkingSpot: ParkingSpot
):ClusterItem {
    override fun getPosition(): LatLng {
        return parkingSpot.location
    }

    override fun getTitle(): String {
        return parkingSpot.zoneCode
    }

    override fun getSnippet(): String? {
        return null
    }

    override fun getZIndex(): Float? {
        return null
    }
}