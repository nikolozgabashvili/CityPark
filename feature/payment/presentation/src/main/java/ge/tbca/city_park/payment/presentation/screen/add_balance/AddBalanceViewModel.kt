package ge.tbca.city_park.payment.presentation.screen.add_balance

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.payment.domain.usecase.BuyBalanceUseCase
import ge.tbca.city_park.payment.domain.usecase.GetAllCreditCardsUseCase
import ge.tbca.city_park.payment.presentation.mapper.toPresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBalanceViewModel @Inject constructor(
    private val getCardsUseCase: GetAllCreditCardsUseCase,
    private val buyBalanceUseCase: BuyBalanceUseCase
) : BaseViewModel<AddBalanceScreenState, AddBalanceEffect, AddBalanceEvent>(AddBalanceScreenState()) {


    init {
        getCards()
    }

    override fun onEvent(event: AddBalanceEvent) {

        when (event) {
            is AddBalanceEvent.NavigateBack -> navigateBack()
            is AddBalanceEvent.Retry -> retry()
            is AddBalanceEvent.ChooseCard -> showCardDropDown()
            is AddBalanceEvent.CloseDropDown -> closeDropDown()
            is AddBalanceEvent.CardSelected -> cardSelected(event.cardId)
            is AddBalanceEvent.NavigateToAddCard -> navigateToAddCard()
            is AddBalanceEvent.TransactionAmountChanged -> updateTransactionAmount(event.amount)
            AddBalanceEvent.StartTransaction -> startTransaction()
        }

    }

    private fun startTransaction() {
        viewModelScope.launch {

            updateState {
                copy(
                    showTransactionAmountError = state.transactionAmount.isEmpty()
                )
            }

            if (state.selectedCard==null){
                sendSideEffect(AddBalanceEffect.CardNotSelected)
            }

            if (state.canStartTransaction) {
                buyBalanceUseCase(
                    cardId = state.selectedCard?.id!!,
                    amount = state.transactionAmount.toDouble()
                ).collect { resource ->
                    updateState { copy(transactionInProgress = resource.isLoading()) }

                    when (resource) {
                        Resource.Loading -> Unit
                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(AddBalanceEffect.Error(error))
                        }

                        is Resource.Success -> {
                            sendSideEffect(AddBalanceEffect.Success)
                            updateState { copy(transactionAmount = "") }
                        }
                    }

                }
            }


        }
    }

    private fun updateTransactionAmount(amount: String) {
        val filtered = amount.filter { it.isDigit() && it.digitToInt() > 0 }
        updateState { copy(transactionAmount = filtered, showTransactionAmountError = false) }
    }

    private fun navigateToAddCard() {
        updateState { copy(showDropDown = false) }
        viewModelScope.launch {
            sendSideEffect(AddBalanceEffect.NavigateToAddCard)
        }
    }

    private fun cardSelected(cardId: Int) {
        closeDropDown()
        updateState { copy(selectedCardId = cardId) }
    }

    private fun closeDropDown() {
        updateState { copy(showDropDown = false) }
    }

    private fun showCardDropDown() {
        updateState { copy(showDropDown = true) }
    }

    private fun retry() {
        getCards()

    }

    private fun getCards() {
        viewModelScope.launch {
            getCardsUseCase().collect { resource ->

                updateState { copy(loading = resource.isLoading(), error = null) }
                when (resource) {
                    Resource.Loading -> Unit
                    is Resource.Error -> {
                        val error = resource.error.toGenericString()
                        updateState { copy(error = error) }
                        sendSideEffect(AddBalanceEffect.Error(error))
                    }

                    is Resource.Success -> {
                        val cards = resource.data.toPresenter()
                        updateState {
                            copy(
                                cards = cards
                            )
                        }
                    }
                }
            }
        }

    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(AddBalanceEffect.NavigateBack)
        }
    }
}