package ge.tbca.city_park.parking.presentation.screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.cars.domain.usecase.GetAllCarsUseCase
import ge.tbca.city_park.cars.presentation.mapper.toPresenter
import ge.tbca.city_park.core.domain.util.ApiError
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.parking.domain.usecase.GetParkingSpotByZoneCodeUseCase
import ge.tbca.city_park.parking.domain.usecase.GetParkingSpotsUseCase
import ge.tbca.city_park.parking.presentation.mapper.toPresenter
import ge.tbca.city_park.parking.presentation.model.ParkingClusterUi
import ge.tbca.city_park.reservation.domain.model.ReservationRequest
import ge.tbca.city_park.reservation.domain.usecase.CreateReservationUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getParkingSpotsUseCase: GetParkingSpotsUseCase,
    private val getParkingSpotByZoneCodeUseCase: GetParkingSpotByZoneCodeUseCase,
    private val getAllCarsUseCase: GetAllCarsUseCase,
    private val createReservationUseCase: CreateReservationUseCase
) : BaseViewModel<MapState, MapEffect, MapEvent>(MapState()) {


    override fun onEvent(event: MapEvent) {

        when (event) {
            is MapEvent.MapLoaded -> mapLoaded()
            is MapEvent.OnParkingSpotClick -> parkingSpotClicked(event.id)
            is MapEvent.DismissBottomSheet -> dismissMapBottomSheet()
            is MapEvent.HideCarBottomSheet -> hideCarBottomSheet()
            is MapEvent.NavigateToAddBalance -> navigateToAddBalance()
            is MapEvent.NavigateToAddCar -> navigateToAddCar()
            is MapEvent.OnCarSelected -> onCarSelected(event.id)
            is MapEvent.ShowCarBottomSheet -> showCarBottomSheet()
            is MapEvent.StartParking -> startParking()
            is MapEvent.DismissAlertDialog -> dismissAlertDialog()
        }

    }

    private fun dismissAlertDialog() {
        updateState { copy(showInsufficientBalanceDialog = false) }
    }

    private fun fetchInfo() {
        fetchCars()
        fetchParkingSpots()
    }

    private fun showCarBottomSheet() {
        updateState { copy(showCarBottomSheet = true) }
    }

    private fun hideCarBottomSheet() {
        updateState { copy(showCarBottomSheet = false, selectedCarId = null) }
    }

    private fun onCarSelected(id: Int) {
        updateState { copy(selectedCarId = id, showCarBottomSheet = false) }
    }

    private fun dismissMapBottomSheet() {
        updateState { copy(selectedParkingSpotId = null) }
    }

    private fun parkingSpotClicked(id: Int) {
        updateState { copy(selectedParkingSpotId = id) }
    }

    private fun mapLoaded() {
        fetchInfo()
        updateState { copy(showInsufficientBalanceDialog = false) }
    }

    private fun navigateToAddCar() {
        viewModelScope.launch {
            sendSideEffect(MapEffect.NavigateToAddCar)
        }

    }

    private fun navigateToAddBalance() {
        viewModelScope.launch {
            sendSideEffect(MapEffect.NavigateToAddBalance)
        }

    }

    private fun startParking() {
        with(state) {
            selectedParkingSpot?.let { selectedSpot ->
                selectedCar?.let { selectedCar ->
                    viewModelScope.launch {
                        updateState {
                            copy(
                                showCarBottomSheet = false,
                                selectedCarId = null,
                                selectedParkingSpotId = null
                            )
                        }
                        createReservationUseCase(
                            reservation = ReservationRequest(
                                zoneCode = selectedSpot.zoneCode,
                                carNumber = selectedCar.plateNumber,
                            )
                        ).collect { resource ->
                            updateState { copy(reservationLoading = resource.isLoading()) }
                            when (resource) {
                                is Resource.Error -> {

                                    updateState { copy(showInsufficientBalanceDialog = resource.error == ApiError.INSUFFICIENT_BALANCE) }

                                    val error = resource.error.toGenericString()
                                    sendSideEffect(MapEffect.Error(error))
                                }

                                is Resource.Success -> {
                                    updateState {
                                        copy(
                                            selectedParkingSpotId = null,
                                            selectedCarId = null,
                                            showCarBottomSheet = false
                                        )
                                    }
                                    sendSideEffect(MapEffect.ReservationCreated)
                                }

                                is Resource.Loading -> Unit
                            }
                        }
                    }
                }
            }

        }

    }

    private fun fetchCars() {
        viewModelScope.launch {
            getAllCarsUseCase().collect { resource ->
                updateState { copy(carsLoading = resource.isLoading()) }

                when (resource) {
                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        sendSideEffect(MapEffect.Error(error))

                    }

                    is Resource.Success -> {
                        val cars = resource.data.toPresenter()
                        updateState { copy(cars = cars) }

                    }

                    is Resource.Loading -> Unit
                }

            }
        }

    }

    private fun fetchParkingSpots() {
        viewModelScope.launch {
            getParkingSpotsUseCase().collect { resource ->
                updateState { copy(parkingSpotsLoading = resource.isLoading()) }
                when (resource) {
                    is Resource.Success -> {
                        val parkingSpots = resource.data.toPresenter()
                        val clusters = parkingSpots.map { ParkingClusterUi(it) }
                        updateState {
                            copy(
                                parkingSpots = parkingSpots,
                                parkingClusters = clusters
                            )
                        }
                    }

                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        sendSideEffect(MapEffect.Error(error))
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }

    private fun getParkingSpotByZoneCode(zoneCode: String) {
        viewModelScope.launch {
            getParkingSpotByZoneCodeUseCase(zoneCode).collect { resource ->
                updateState { copy(parkingSpotsLoading = resource.isLoading()) }
                when (resource) {
                    is Resource.Success -> {}
                    is Resource.Error -> {}
                    is Resource.Loading -> Unit
                }
            }
        }
    }
}