package ge.tbca.city_park.fines.presentation.screen.fine_detail

import ge.tbca.city_park.core.ui.util.GenericString

sealed interface FineDetailEffect {
    data class Error(val error: GenericString) : FineDetailEffect
    data object NavigateBack : FineDetailEffect
    data object PaymentSuccess : FineDetailEffect
    data object NavigateToAddCard : FineDetailEffect
}