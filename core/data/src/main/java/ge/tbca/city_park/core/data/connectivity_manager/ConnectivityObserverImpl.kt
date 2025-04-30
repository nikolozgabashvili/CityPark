package ge.tbca.city_park.core.data.connectivity_manager

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import androidx.core.content.getSystemService
import dagger.hilt.android.qualifiers.ApplicationContext
import ge.tbca.city_park.core.domain.connectivity_manager.ConnectivityObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

@SuppressLint("MissingPermission")
class ConnectivityObserverImpl @Inject constructor(
    @ApplicationContext context: Context
) : ConnectivityObserver {

    override val isConnected: Flow<Boolean> = callbackFlow {
        val connectivityManager = context.getSystemService<ConnectivityManager>()
        if (connectivityManager == null) {
            channel.trySend(false)
            channel.close()
            return@callbackFlow
        }

        val callback = object : NetworkCallback() {

            override fun onAvailable(network: Network) {
                channel.trySend(true)
            }

            override fun onLost(network: Network) {
                channel.trySend(false)
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val connected = networkCapabilities.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_VALIDATED
                )
                trySend(connected)
            }
        }
        connectivityManager.registerDefaultNetworkCallback(callback)

        channel.trySend(connectivityManager.isCurrentlyConnected())

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
        .flowOn(Dispatchers.IO)
        .conflate()

    private fun ConnectivityManager.isCurrentlyConnected() =
        activeNetwork?.let(::getNetworkCapabilities)
            ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            ?: false
}