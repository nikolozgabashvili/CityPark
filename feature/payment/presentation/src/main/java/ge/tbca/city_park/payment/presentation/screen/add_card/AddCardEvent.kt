package ge.tbca.city_park.payment.presentation.screen.add_card

sealed interface AddCardEvent {
    data class CardNumberChanged(val number: String) : AddCardEvent
    data class ExpireDateChanged(val date: String) : AddCardEvent
    data class CvvChanged(val cvv: String) : AddCardEvent
    data class CardHolderNameChanged(val name: String) : AddCardEvent
    data object SaveCardButtonClicked : AddCardEvent
    data object BackButtonClicked : AddCardEvent
}