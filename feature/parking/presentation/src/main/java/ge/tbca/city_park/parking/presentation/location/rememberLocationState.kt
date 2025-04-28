package ge.tbca.city_park.parking.presentation.location

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.RECEIVER_NOT_EXPORTED
import android.content.Intent
import android.content.IntentFilter
import android.location.LocationManager
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.core.location.LocationManagerCompat


object  LocationHelper{
    @Composable
    fun rememberLocationState(): State<Boolean> {
        val context = LocalContext.current
        val locationState = rememberSaveable { mutableStateOf(context.isDeviceLocationEnabled()) }

        DisposableEffect(Unit) {
            val receiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    locationState.value = context.isDeviceLocationEnabled()
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                context.registerReceiver(
                    receiver,
                    IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION),
                    RECEIVER_NOT_EXPORTED
                )
            } else {
                context.registerReceiver(
                    receiver,
                    IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
                )
            }

            onDispose { context.unregisterReceiver(receiver) }
        }

        return locationState
    }

    fun Context.isDeviceLocationEnabled(): Boolean {
        return try {
            val locationManager = getSystemService(Context.LOCATION_SERVICE) as? LocationManager?
            locationManager?.let {
                LocationManagerCompat.isLocationEnabled(locationManager)
            } ?: false
        } catch (e: Exception) {
            false
        }
    }
}
