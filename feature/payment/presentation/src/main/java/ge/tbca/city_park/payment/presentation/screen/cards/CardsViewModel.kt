package ge.tbca.city_park.payment.presentation.screen.cards

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.payment.domain.usecase.GetAllCreditCardsUseCase
import ge.tbca.city_park.payment.presentation.mapper.toPresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val getAllCreditCardsUseCase: GetAllCreditCardsUseCase
) : BaseViewModel<CardsState, CardsEffect, CardsEvent>(CardsState()) {

    init {
        fetchCards()
    }

    override fun onEvent(event: CardsEvent) {
        when (event) {
            is CardsEvent.NavigateBack -> navigateBack()
        }
    }

    private fun fetchCards() {
        viewModelScope.launch {
            getAllCreditCardsUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading()) }
                when (resource) {
                    is Resource.Success -> {
                        updateState {
                            copy(cardsList = resource.data.map { it.toPresenter() })
                        }
                    }

                    is Resource.Error -> Unit

                    is Resource.Loading -> Unit
                }
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(CardsEffect.NavigateBack)
        }
    }
}