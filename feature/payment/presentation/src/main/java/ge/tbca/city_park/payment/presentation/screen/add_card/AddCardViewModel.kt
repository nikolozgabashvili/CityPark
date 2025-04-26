package ge.tbca.city_park.payment.presentation.screen.add_card

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.mapper.toGenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.core.domain.util.isLoading
import ge.tbca.city_park.payment.domain.usecase.AddCreditCardUseCase
import ge.tbca.city_park.payment.domain.usecase.ValidateCardCvvUseCase
import ge.tbca.city_park.payment.domain.usecase.ValidateCardExpireDateUseCase
import ge.tbca.city_park.payment.domain.usecase.ValidateCardHolderNameUseCase
import ge.tbca.city_park.payment.domain.usecase.ValidateCardNumberUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val validateCardNumberUseCase: ValidateCardNumberUseCase,
    private val validateExpireDateUseCase: ValidateCardExpireDateUseCase,
    private val validateCvvUseCase: ValidateCardCvvUseCase,
    private val validateCardHolderNameUseCase: ValidateCardHolderNameUseCase,
    private val addCreditCardUseCase: AddCreditCardUseCase
) : BaseViewModel<AddCardState, AddCardEffect, AddCardEvent>(AddCardState()) {

    override fun onEvent(event: AddCardEvent) {
        when (event) {
            is AddCardEvent.BackButtonClicked -> navigateBack()
            is AddCardEvent.SaveCardButtonClicked -> saveCard()
            is AddCardEvent.CardHolderNameChanged -> updateCardHolderName(event.name)
            is AddCardEvent.CardNumberChanged -> updateCardNumber(event.number)
            is AddCardEvent.CvvChanged -> updateCvv(event.cvv)
            is AddCardEvent.ExpireDateChanged -> updateExpireDate(event.date)
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            sendSideEffect(AddCardEffect.NavigateBack)
        }
    }

    private fun saveCard() {
        val isCardNumberValid = validateCardNumberUseCase(state.cardNumber)
        val isExpireDateValid = validateExpireDateUseCase(state.expireDate)
        val isCvvValid = validateCvvUseCase(state.cvv)
        val isCardHolderNameValid = validateCardHolderNameUseCase(state.cardHolderName)

        if (isCardNumberValid && isExpireDateValid && isCvvValid && isCardHolderNameValid) {
            updateState { copy(isLoading = true) }
            viewModelScope.launch {
                addCreditCardUseCase(
                    cardNumber = state.cardNumber,
                    holderName = state.cardHolderName,
                    expirationMonth = state.expireDate.substring(0, 2).toInt(),
                    expirationYear = state.expireDate.substring(2, 4).toInt(),
                    cvv = state.cvv,
                ).collect { resource ->
                    updateState { copy(isLoading = resource.isLoading()) }
                    when (resource) {

                        is Resource.Success -> {
                            sendSideEffect(AddCardEffect.Success)
                        }

                        is Resource.Error -> {
                            val error = resource.error.toGenericString()
                            sendSideEffect(
                                AddCardEffect.Error(
                                    error
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
                    showCardNumberError = !isCardNumberValid,
                    showExpireDateError = !isExpireDateValid,
                    showCvvError = !isCvvValid,
                    showCardHolderNameError = !isCardHolderNameValid
                )
            }
        }
    }

    private fun updateCardHolderName(name: String) {
        val filtered = name.filter { it.isLetter() || it == ' ' }
        updateState { copy(cardHolderName = filtered, showCardHolderNameError = false) }
    }

    private fun updateCardNumber(number: String) {
        val filtered = number.filter { it.isDigit() }.take(16)
        updateState { copy(cardNumber = filtered, showCardNumberError = false) }
    }

    private fun updateCvv(cvv: String) {
        val filtered = cvv.filter { it.isDigit() }.take(3)
        updateState { copy(cvv = filtered, showCvvError = false) }
    }

    private fun updateExpireDate(date: String) {
        val filteredDate = date.filter { it.isDigit() }.take(4)
        updateState { copy(expireDate = filteredDate, showExpireDateError = false) }
    }
}