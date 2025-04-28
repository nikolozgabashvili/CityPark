package ge.tbca.city_park.cars.presentation.screen.add_car

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.cars.domain.model.AddCarRequestDomain
import ge.tbca.city_park.cars.domain.usecase.AddCarUseCase
import ge.tbca.city_park.cars.domain.usecase.ValidateCarNameUseCase
import ge.tbca.city_park.cars.domain.usecase.ValidatePersonalNumberUseCase
import ge.tbca.city_park.cars.domain.usecase.ValidatePlateNumberUseCase
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCarViewModel @Inject constructor(
    private val validatePersonalNumberUseCase: ValidatePersonalNumberUseCase,
    private val validatePlateNumberUseCase: ValidatePlateNumberUseCase,
    private val validateCarNameUseCase: ValidateCarNameUseCase,
    private val addCarUseCase: AddCarUseCase
) : BaseViewModel<AddCarState, AddCarEffect, AddCarEvent>(AddCarState()) {

    override fun onEvent(event: AddCarEvent) {
        when (event) {
            is AddCarEvent.CarNameChanged -> updateCarName(event.carName)
            is AddCarEvent.OwnerPersonalNumberChanged -> updateOwnerPersonalNumber(event.ownerPersonalNumber)
            is AddCarEvent.PlateNumberChanged -> updatePlateNumber(event.plateNumber)
            is AddCarEvent.CheckBoxChanged -> updateCheckBox()
            is AddCarEvent.BackButtonClicked -> navigateBack()
            is AddCarEvent.SaveCarButtonClicked -> saveCar()
        }
    }

    private fun updateCarName(carName: String) {
        updateState { copy(carName = carName, showCarNameError = false) }
    }

    private fun updateOwnerPersonalNumber(number: String) {
        val filtered = number.filter { it.isDigit() }.take(11)
        updateState { copy(ownerPersonalNumber = filtered, showOwnerPersonalNumberError = false) }
    }

    private fun updatePlateNumber(plateNumber: String) {
        updateState { copy(plateNumber = plateNumber, showPlateNumberError = false) }
    }

    private fun updateCheckBox() {
        updateState { copy(isChecked = !isChecked) }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(AddCarEffect.NavigateBack)
        }
    }

    private fun saveCar() {
        updateState { copy(isLoading = true) }

        val isCarNameValid = validateCarNameUseCase(state.carName)
        val isOwnerPersonalNumberValid = validatePersonalNumberUseCase(state.ownerPersonalNumber)
        val isPlateNumberValid = validatePlateNumberUseCase(state.plateNumber)
        val isChecked = state.isChecked

        if (isOwnerPersonalNumberValid && isPlateNumberValid && (isChecked && isCarNameValid || !isChecked)) {
            val car = AddCarRequestDomain(
                name = state.carName,
                carNumber =state.plateNumber,
                ownerPersonalId = state.ownerPersonalNumber
            )

            viewModelScope.launch {
                addCarUseCase(car).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }
                    when (resource) {
                        is Resource.Success -> {
                            sendSideEffect(AddCarEffect.Success)
                        }

                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(
                                AddCarEffect.ShowSnackbar(
                                    message = error
                                )
                            )
                        }

                        is Resource.Loading -> Unit
                    }
                }
            }
        } else {
            updateState {
                copy(
                    showCarNameError = isChecked && !isCarNameValid,
                    showOwnerPersonalNumberError = !isOwnerPersonalNumberValid,
                    showPlateNumberError = !isPlateNumberValid,
                    isLoading = false
                )
            }
        }
    }
}