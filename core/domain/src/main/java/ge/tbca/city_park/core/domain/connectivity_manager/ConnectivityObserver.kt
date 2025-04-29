package ge.tbca.city_park.core.domain.connectivity_manager

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    val isConnected: Flow<Boolean>
}