package ge.tbca.city_park.payment.presentation.screen.add_card

import ge.tbca.city_park.core.ui.util.GenericString

sealed interface AddCardEffect {
    data object NavigateBack : AddCardEffect
    data object Success : AddCardEffect
    data class Error(val message: GenericString) : AddCardEffect
}