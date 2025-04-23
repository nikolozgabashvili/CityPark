package ge.tbca.city_park.payment.presentation.screen.add_card

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import ge.tbca.citi_park.core.ui.util.GenericString
import ge.tbca.city_park.core.domain.util.Resource
import ge.tbca.city_park.payment.domain.usecase.AddCreditCardUseCase
import ge.tbca.city_park.payment.domain.usecase.ValidateCardCvvUseCase
import ge.tbca.city_park.payment.domain.usecase.ValidateCardExpireDateUseCase
import ge.tbca.city_park.payment.domain.usecase.ValidateCardHolderNameUseCase
import ge.tbca.city_park.payment.domain.usecase.ValidateCardNumberUseCase
import ge.tbca.city_park.payment.presentation.extension.toGenericString
import ge.tbca.city_park.payment.presentation.mapper.toDomain
import ge.tbca.city_park.payment.presentation.model.CreditCardUi
import kotlinx.coroutines.launch
import java.util.UUID
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
        updateState { copy(isLoading = true) }

        val isCardNumberValid = validateCardNumberUseCase(state.cardNumber)
        val isExpireDateValid = validateExpireDateUseCase(state.expireDate)
        val isCvvValid = validateCvvUseCase(state.cvv)
        val isCardHolderNameValid = validateCardHolderNameUseCase(state.cardHolderName)

        if (isCardNumberValid && isExpireDateValid && isCvvValid && isCardHolderNameValid) {
            val card = CreditCardUi(
                id = UUID.randomUUID().toString(),
                cardNumber = state.cardNumber,
                expireDate = state.expireDate,
                cvv = state.cvv,
                cardHolderName = state.cardHolderName
            ).toDomain()

            viewModelScope.launch {
                addCreditCardUseCase(card).collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            updateState { copy(isLoading = false) }
                            sendSideEffect(
                                AddCardEffect.ShowSnackbar(
                                    GenericString.DynamicString("Card added successfully")
                                )
                            )
                            sendSideEffect(AddCardEffect.NavigateBack)
                        }

                        is Resource.Error -> {
                            updateState { copy(isLoading = false) }
                            sendSideEffect(AddCardEffect.ShowSnackbar(resource.error.toGenericString()))
                        }

                        is Resource.Loading -> updateState { copy(isLoading = true) }
                    }
                }
            }
        } else {
            updateState {
                copy(
                    showCardNumberError = !isCardNumberValid,
                    showExpireDateError = !isExpireDateValid,
                    showCvvError = !isCvvValid,
                    showCardHolderNameError = !isCardHolderNameValid,
                    isLoading = false
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