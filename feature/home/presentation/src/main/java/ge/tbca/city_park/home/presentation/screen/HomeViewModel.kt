package ge.tbca.city_park.home.presentation.screen


import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.cars.domain.usecase.DeleteCarByIdUseCase
import ge.tbca.city_park.cars.domain.usecase.GetAllCarsUseCase
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.home.presentation.mapper.toPresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCarsUseCase: GetAllCarsUseCase,
    private val deleteCarByIdUseCase: DeleteCarByIdUseCase,

    ) : BaseViewModel<HomeState, HomeEffect, HomeEvent>(HomeState()) {

    override fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.AddCarButtonClicked -> {
                navigateToAddCar()

            }

            is HomeEvent.CarClicked -> {
                viewModelScope.launch {
                    deleteCarByIdUseCase(event.carId).collect {
                        if (it is Resource.Success) {
                            fetchCars()
                        }
                    }
                }

            }

            HomeEvent.Refresh -> {
                fetchCars()
            }
        }
    }

    private fun navigateToAddCar() {
        viewModelScope.launch {
            sendSideEffect(HomeEffect.NavigateToAddCar)
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

                        sendSideEffect(HomeEffect.Error(error))

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