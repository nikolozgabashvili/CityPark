package ge.tbca.city_park.cars.presentation.screen.add_car

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.util.GenericString
import ge.tbca.city_park.cars.domain.usecase.AddCarUseCase
import ge.tbca.city_park.cars.domain.usecase.ValidateCarNameUseCase
import ge.tbca.city_park.cars.domain.usecase.ValidatePersonalNumberUseCase
import ge.tbca.city_park.cars.domain.usecase.ValidatePlateNumberUseCase
import ge.tbca.city_park.cars.presentation.extension.toGenericString
import ge.tbca.city_park.cars.presentation.mapper.toDomain
import ge.tbca.city_park.cars.presentation.model.CarUi
import ge.tbca.city_park.core.domain.util.Resource
import kotlinx.coroutines.launch
import java.util.UUID
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
            val car = CarUi(
                id = UUID.randomUUID().toString(),
                carName = if (isChecked) state.carName else null,
                ownerPersonalNumber = state.ownerPersonalNumber,
                plateNumber = state.plateNumber
            ).toDomain()

            viewModelScope.launch {
                addCarUseCase(car).collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            updateState { copy(isLoading = false) }
                            sendSideEffect(
                                AddCarEffect.ShowSnackbar(
                                    GenericString.DynamicString("Car added successfully")
                                )
                            )
                            sendSideEffect(AddCarEffect.NavigateBack)
                        }

                        is Resource.Error -> {
                            updateState { copy(isLoading = false) }
                            sendSideEffect(AddCarEffect.ShowSnackbar(resource.error.toGenericString()))
                        }

                        is Resource.Loading -> updateState { copy(isLoading = true) }
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