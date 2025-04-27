package ge.tbca.city_park.home.presentation.screen

import ge.tbca.citi_park.core.ui.util.GenericString

data class HomeState(
    val userBalance: Double? = null,
    val isLoading: Boolean = false,
    val error:GenericString? = null,
)