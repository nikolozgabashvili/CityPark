package ge.tbca.city_park.payment.presentation.screen.add_balance

import ge.tbca.city_park.core.ui.util.GenericString

sealed interface AddBalanceEffect {
    data object NavigateBack : AddBalanceEffect
    data object NavigateToAddCard : AddBalanceEffect
    data object CardNotSelected : AddBalanceEffect
    data object Success : AddBalanceEffect
    data class Error(val message: GenericString) : AddBalanceEffect
}