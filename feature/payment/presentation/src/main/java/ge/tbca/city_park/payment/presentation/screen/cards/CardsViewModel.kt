package ge.tbca.city_park.payment.presentation.screen.cards

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.payment.domain.usecase.DeleteCreditCardByIdUseCase
import ge.tbca.city_park.payment.domain.usecase.GetAllCreditCardsUseCase
import ge.tbca.city_park.payment.presentation.mapper.toPresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val getAllCreditCardsUseCase: GetAllCreditCardsUseCase,
    private val deleteCreditCardByIdUseCase: DeleteCreditCardByIdUseCase
) : BaseViewModel<CardsState, CardsEffect, CardsEvent>(CardsState()) {

    init {
        fetchCards()
    }

    override fun onEvent(event: CardsEvent) {
        when (event) {
            is CardsEvent.NavigateBack -> navigateBack()
            is CardsEvent.Refresh -> refresh()
            is CardsEvent.AddCardButtonClicked -> navigateToAddCard()
            is CardsEvent.DeleteCardClicked -> showDeleteDialog(event.cardId)
            is CardsEvent.DismissDeleteCardDialog -> dismissDeleteDialog()
            is CardsEvent.DeleteCard -> deleteCard()
        }
    }

    private fun refresh() {
        fetchCards()
    }

    private fun fetchCards() {
        viewModelScope.launch {
            getAllCreditCardsUseCase().collect { resource ->
                updateState { copy(isLoading = resource.isLoading(), error = null) }
                when (resource) {
                    is Resource.Success -> {
                        val cards = resource.data.toPresenter()
                        updateState {
                            copy(
                                cardsList = cards,
                                noCards = cards.isEmpty()
                            )
                        }
                    }

                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        if (state.cardsList.isEmpty()) {
                            updateState { copy(error = error) }
                        }
                        sendSideEffect(CardsEffect.ShowSnackbar(error))
                    }

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

    private fun navigateToAddCard() {
        viewModelScope.launch {
            sendSideEffect(CardsEffect.NavigateToAddCard)
        }
    }

    private fun showDeleteDialog(deleteCardId: Int) {
        updateState {
            copy(deleteCardId = deleteCardId, showDeleteCardDialog = true)
        }
    }

    private fun dismissDeleteDialog() {
        updateState {
            copy(showDeleteCardDialog = false)
        }
    }

    private fun deleteCard() {
        viewModelScope.launch {
            state.deleteCardId?.let {
                deleteCreditCardByIdUseCase(it).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }
                    when (resource) {
                        is Resource.Success -> {
                            dismissDeleteDialog()
                            fetchCards()
                        }
                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(CardsEffect.ShowSnackbar(error))
                            dismissDeleteDialog()
                        }
                        Resource.Loading -> Unit
                    }
                }
            }
        }
    }
}