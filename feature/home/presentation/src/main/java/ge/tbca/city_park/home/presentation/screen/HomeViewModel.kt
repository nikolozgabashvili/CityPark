package ge.tbca.city_park.home.presentation.screen

import ge.tbca.citi_park.core.ui.base.BaseViewModel

class HomeViewModel : BaseViewModel<HomeState, HomeEffect, HomeEvent>(HomeState()) {

    override fun onEvent(event: HomeEvent) {
        when(event) {
            else -> {}
        }
    }
}