package ge.tbca.city_park.core.domain.usecase

import ge.tbca.city_park.core.domain.connectivity_manager.ConnectivityObserver
import javax.inject.Inject

class IsNetworkAvailableUseCase @Inject constructor(
    private val connectivityObserver: ConnectivityObserver
) {
    operator fun invoke() = connectivityObserver.isConnected
}