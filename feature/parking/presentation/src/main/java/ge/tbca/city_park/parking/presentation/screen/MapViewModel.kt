package ge.tbca.city_park.parking.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.parking.domain.usecase.GetParkingSpotByZoneCodeUseCase
import ge.tbca.city_park.parking.domain.usecase.GetParkingSpotsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getParkingSpotsUseCase: GetParkingSpotsUseCase,
    private val getParkingSpotByZoneCodeUseCase: GetParkingSpotByZoneCodeUseCase
) : BaseViewModel<MapState, MapEffect, MapEvent>(MapState()) {

    init {
        fetchParkingSpots()
    }

    override fun onEvent(event: MapEvent) {

    }

    private fun fetchParkingSpots() {
        viewModelScope.launch {
            getParkingSpotsUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }
                when (resource) {
                    is Resource.Success -> {}
                    is Resource.Error -> {}
                    is Resource.Loading -> Unit
                }
            }
        }
    }

    private fun getParkingSpotByZoneCode(zoneCode: String) {
        viewModelScope.launch {
            getParkingSpotByZoneCodeUseCase(zoneCode).collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }
                when (resource) {
                    is Resource.Success -> {}
                    is Resource.Error -> {}
                    is Resource.Loading -> Unit
                }
            }
        }
    }
}