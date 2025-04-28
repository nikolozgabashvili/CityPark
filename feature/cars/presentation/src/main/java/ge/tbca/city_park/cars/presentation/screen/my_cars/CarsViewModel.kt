package ge.tbca.city_park.cars.presentation.screen.my_cars

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.cars.domain.usecase.DeleteCarByIdUseCase
import ge.tbca.city_park.cars.domain.usecase.GetAllCarsUseCase
import ge.tbca.city_park.cars.presentation.mapper.toPresenter
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val getAllCarsUseCase: GetAllCarsUseCase,
    private val deleteCarByIdUseCase: DeleteCarByIdUseCase,

    ) : BaseViewModel<CarsState, CarsEffect, CarsEvent>(CarsState()) {

    init {
        fetchCars()
    }

    override fun onEvent(event: CarsEvent) {
        when (event) {
            is CarsEvent.BackButtonClicked -> navigateBack()

            is CarsEvent.AddCarButtonClicked -> navigateToAddCar()

            is CarsEvent.Refresh -> fetchCars()

            is CarsEvent.DeleteCarClicked -> showDeleteDialog(event.carId)

            is CarsEvent.DismissDeleteCarDialog -> dismissDeleteDialog()

            is CarsEvent.DeleteCar -> deleteCar()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(CarsEffect.NavigateBack)
        }
    }

    private fun navigateToAddCar() {
        viewModelScope.launch {
            sendSideEffect(CarsEffect.NavigateToAddCar)
        }
    }

    private fun fetchCars() {
        viewModelScope.launch {
            getAllCarsUseCase().collect { resource ->
                updateState { copy(carsLoading = resource.isLoading(), error = null) }

                when (resource) {
                    is Resource.Error -> {

                        val error = resource.error.toGenericString()
                        if (state.cars.isEmpty()) {
                            updateState { copy(error = error) }

                        }

                        sendSideEffect(CarsEffect.Error(error))

                    }

                    is Resource.Success -> {
                        val carsList = resource.data.toPresenter()

                        updateState { copy(cars = carsList, noCars = carsList.isEmpty()) }
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }

    private fun showDeleteDialog(carId: Int) {
        updateState { copy(deleteCarId = carId, showDeleteCarDialog = true) }
    }

    private fun dismissDeleteDialog() {
        updateState { copy(showDeleteCarDialog = false) }
    }

    private fun deleteCar() {
        viewModelScope.launch {
            state.deleteCarId?.let {
                deleteCarByIdUseCase(it).collect { resource ->
                    updateState { copy(carsLoading = resource.isLoading()) }
                    when (resource) {
                        is Resource.Success -> {
                            dismissDeleteDialog()
                            fetchCars()
                        }

                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(CarsEffect.Error(error))
                            dismissDeleteDialog()
                        }

                        is Resource.Loading -> Unit
                    }
                }
            }
        }
    }
}