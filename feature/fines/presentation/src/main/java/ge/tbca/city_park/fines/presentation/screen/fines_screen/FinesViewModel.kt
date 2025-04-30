package ge.tbca.city_park.fines.presentation.screen.fines_screen

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.fines.domain.usecase.GetParkingFinesUseCase
import ge.tbca.city_park.fines.presentation.mapper.toPresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinesViewModel @Inject constructor(
    private val getParkingFinesUseCase: GetParkingFinesUseCase
) : BaseViewModel<FinesState, FinesEffect, FinesEvent>(FinesState()) {

    init {
        loadFines()
    }

    override fun onEvent(event: FinesEvent) {
        when (event) {
            is FinesEvent.BackButtonClicked -> navigateBack()
            is FinesEvent.FineClicked -> navigateToFineDetail(event.fineId)
            is FinesEvent.Refresh -> loadFines()
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(FinesEffect.NavigateBack)
        }
    }

    private fun navigateToFineDetail(fineId: Int) {
        viewModelScope.launch {
            sendSideEffect(FinesEffect.NavigateToFineDetail(fineId))
        }
    }

    private fun loadFines() {
        viewModelScope.launch {
            getParkingFinesUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }

                when (resource) {
                    is Resource.Success -> {
                        val fines = resource.data.map { it.toPresenter() }
                        updateState {
                            copy(
                                fines = fines,
                                error = null
                            )
                        }
                    }

                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState { copy(error = error) }
                        sendSideEffect(FinesEffect.Error(error))
                    }

                    is Resource.Loading -> Unit
                }
            }
        }
    }
}