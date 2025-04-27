package ge.tba.city_park.reservation.presentation.screen.create_reservation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.cars.domain.usecase.GetAllCarsUseCase
import ge.tbca.city_park.cars.presentation.mapper.toPresenter
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.reservation.domain.model.ReservationRequest
import ge.tbca.city_park.reservation.domain.usecase.CreateReservationUseCase
import ge.tbca.city_park.reservation.domain.usecase.ValidateZoneCodeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateReservationViewModel @Inject constructor(
    private val validateZoneCodeUseCase: ValidateZoneCodeUseCase,
    private val createReservationUseCase: CreateReservationUseCase,
    private val getAllCarsUseCase: GetAllCarsUseCase
) : BaseViewModel<CreateReservationState, CreateReservationEffect, CreateReservationEvent>(
    CreateReservationState()
) {

    init {
        getAllCars()
    }

    override fun onEvent(event: CreateReservationEvent) {
        when (event) {
            is CreateReservationEvent.ZoneCodeChanged -> updateZoneCode(event.zoneCode)
            is CreateReservationEvent.BackButtonClicked -> navigateBack()
            is CreateReservationEvent.ChooseOnMapButtonClicked -> navigateToMap()
            is CreateReservationEvent.CreateReservationButtonClicked -> createReservation()
            is CreateReservationEvent.CarSelected -> cardSelected(event.id)
            is CreateReservationEvent.CloseBottomSheet -> closeBottomSheet()
            is CreateReservationEvent.ShowBottomSheet -> showBottomSheet()
            is CreateReservationEvent.NavigateToAddCar -> navigateToAddCar()
            is CreateReservationEvent.Retry -> retry()
        }
    }

    private fun updateZoneCode(zoneCode: String) {
        updateState { copy(zoneCode = zoneCode.take(5).uppercase(), showZoneCodeError = false) }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(CreateReservationEffect.NavigateBack)
        }
    }

    private fun navigateToMap() {
        viewModelScope.launch {
            sendSideEffect(CreateReservationEffect.NavigateToMap)
        }
    }

    private fun createReservation() {
        val plateNumber = state.selectedCar?.plateNumber
        val isZoneCodeValid = validateZoneCodeUseCase(state.zoneCode)
        viewModelScope.launch {
            updateState { copy(showZoneCodeError = !isZoneCodeValid) }
            if (plateNumber == null) {
                sendSideEffect(CreateReservationEffect.NoCarSelected)
                return@launch
            }


            if (isZoneCodeValid) {
                createReservationUseCase(
                    ReservationRequest(
                        zoneCode = state.zoneCode,
                        carNumber = plateNumber
                    )
                ).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }
                    when (resource) {
                        is Resource.Success -> {
                            sendSideEffect(CreateReservationEffect.Success)
                        }

                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(CreateReservationEffect.Error(error))
                        }

                        is Resource.Loading -> Unit
                    }
                }
            }
        }
    }

    private fun showBottomSheet() {
        updateState { copy(showBottomSheet = true) }
    }

    private fun closeBottomSheet() {
        updateState { copy(showBottomSheet = false) }
    }

    private fun cardSelected(carId: Int) {
        closeBottomSheet()
        updateState { copy(selectedCarId = carId) }
    }

    private fun retry() {
        updateState { copy(showZoneCodeError = false, selectedCarId = null) }
        getAllCars()
    }

    private fun getAllCars() {
        viewModelScope.launch {
            getAllCarsUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }

                when (resource) {
                    is Resource.Success -> {
                        val cars = resource.data.toPresenter()
                        updateState { copy(carsList = cars, error = null) }
                    }

                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState { copy(error = error) }
                        sendSideEffect(CreateReservationEffect.Error(error))
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }

    private fun navigateToAddCar() {
        updateState { copy(showBottomSheet = false) }
        viewModelScope.launch {
            sendSideEffect(CreateReservationEffect.NavigateToAddCar)
        }
    }
}