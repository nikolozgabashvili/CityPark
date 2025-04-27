package ge.tbca.city_park.payment.presentation.screen.add_balance

sealed interface AddBalanceEvent {
    data object NavigateBack : AddBalanceEvent
    data object StartTransaction : AddBalanceEvent
    data object Retry : AddBalanceEvent
    data object ChooseCard : AddBalanceEvent
    data object CloseBottomSheet : AddBalanceEvent
    data object NavigateToAddCard : AddBalanceEvent

    data class CardSelected(val cardId: Int) : AddBalanceEvent
    data class TransactionAmountChanged(val amount:String) : AddBalanceEvent

}