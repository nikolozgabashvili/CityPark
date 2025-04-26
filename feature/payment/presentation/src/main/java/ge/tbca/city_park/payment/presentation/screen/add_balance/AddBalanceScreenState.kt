package ge.tbca.city_park.payment.presentation.screen.add_balance

import ge.tbca.citi_park.core.ui.util.GenericString
import ge.tbca.city_park.payment.presentation.model.CreditCardUi

data class AddBalanceScreenState(
    val cards: List<CreditCardUi> = emptyList(),
    val error: GenericString? = null,
    val loading: Boolean = false,
    val transactionInProgress: Boolean = false,
    val showDropDown: Boolean = false,
    val selectedCardId: Int? = null,
    val transactionAmount: String = "",
    val showCardSelectedError: Boolean = false,
    val showTransactionAmountError: Boolean = false,
) {
    val selectedCard: CreditCardUi?
        get() = cards.firstOrNull { it.id == selectedCardId }

    val canStartTransaction: Boolean
        get() = selectedCard != null && transactionAmount.isNotEmpty()
}