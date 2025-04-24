package ge.tbca.city_park.home.presentation.screen


import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
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
                    deleteCarByIdUseCase(event.carId).collect{
                        println(it)
                        if (it is Resource.Success) {
                            fetchCars()
                        }
                    }
                }

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
                updateState { copy(isLoading = resource.isLoading()) }

                when (resource) {
                    is Resource.Error -> {

                    }

                    is Resource.Success -> {
                        updateState { copy(cars = resource.data.toPresenter()) }
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }
}