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
            is CreateReservationEvent.CloseDropDown -> closeDropDown()
            is CreateReservationEvent.ShowDropDown -> showDropDown()
            is CreateReservationEvent.NavigateToAddCar -> navigateToAddCar()
            is CreateReservationEvent.Retry -> retry()
        }
    }

    private fun updateZoneCode(zoneCode: String) {
        updateState { copy(zoneCode = zoneCode.take(5), showZoneCodeError = false) }
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
        val plateNumber = state.selectedCar?.plateNumber ?: ""
        val isZoneCodeValid = validateZoneCodeUseCase(state.zoneCode)
        if (isZoneCodeValid && plateNumber.isNotEmpty()) {
            viewModelScope.launch {
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
        } else {
            updateState { copy(showZoneCodeError = !isZoneCodeValid) }
        }
    }

    private fun showDropDown() {
        updateState { copy(showDropDown = true) }
    }

    private fun closeDropDown() {
        updateState { copy(showDropDown = false) }
    }

    private fun cardSelected(carId: Int) {
        closeDropDown()
        updateState { copy(selectedCarId = carId) }
    }

    private fun retry() {
        getAllCars()
    }

    private fun getAllCars() {
        viewModelScope.launch {
            getAllCarsUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }

                when (resource) {
                    is Resource.Success -> {
                        val cars = resource.data.toPresenter()
                        updateState { copy(carsList = cars) }
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

    private fun navigateToAddCar() {
        viewModelScope.launch {
            updateState { copy(showDropDown = false) }
            sendSideEffect(CreateReservationEffect.NavigateToAddCar)
        }
    }
}