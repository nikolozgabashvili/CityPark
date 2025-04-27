package ge.tbca.city_park.cars.presentation.screen.my_cars


import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
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

    ) : BaseViewModel<CarsScreenState, CarsScreenEffect, CarsScreenEvent>(CarsScreenState()) {

    override fun onEvent(event: CarsScreenEvent) {
        when (event) {
            is CarsScreenEvent.AddCarButtonClicked -> {
                navigateToAddCar()

            }

            is CarsScreenEvent.CarClicked -> {
                viewModelScope.launch {
                    deleteCarByIdUseCase(event.carId).collect {
                        if (it is Resource.Success) {
                            fetchCars()
                        }
                    }
                }

            }

            CarsScreenEvent.Refresh -> {
                fetchCars()
            }

            CarsScreenEvent.BackButtonClicked -> navigateBack()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(CarsScreenEffect.NavigateBack)
        }
    }

    private fun navigateToAddCar() {
        viewModelScope.launch {
            sendSideEffect(CarsScreenEffect.NavigateToAddCar)
        }
    }

    init {
        fetchCars()
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

                        sendSideEffect(CarsScreenEffect.Error(error))

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
}