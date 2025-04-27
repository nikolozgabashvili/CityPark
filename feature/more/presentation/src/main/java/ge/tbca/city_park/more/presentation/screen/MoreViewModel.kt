package ge.tbca.city_park.more.presentation.screen

import androidx.lifecycle.viewModelScope
import ge.tbca.citi_park.core.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoreViewModel @Inject constructor() :
    BaseViewModel<Unit, MoreEffect, MoreEvent>(Unit) {

    override fun onEvent(event: MoreEvent) {
        when(event) {
            MoreEvent.NavigateToCards -> navigateToCards()
            MoreEvent.NavigateToCars -> navigateToCars()
            MoreEvent.NavigateToProfile -> navigateToProfile()
            MoreEvent.NavigateToSettings -> navigateToSettings()
        }
    }

    private fun navigateToCards() {
        viewModelScope.launch {
            sendSideEffect(MoreEffect.NavigateToCards)
        }
    }

    private fun navigateToCars() {
        viewModelScope.launch {
            sendSideEffect(MoreEffect.NavigateToCars)
        }
    }

    private fun navigateToProfile() {
        viewModelScope.launch {
            sendSideEffect(MoreEffect.NavigateToProfile)
        }
    }

    private fun navigateToSettings() {
        viewModelScope.launch {
            sendSideEffect(MoreEffect.NavigateToSettings)
        }
    }
}