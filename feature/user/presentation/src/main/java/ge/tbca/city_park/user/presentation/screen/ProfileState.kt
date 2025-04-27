package ge.tbca.city_park.user.presentation.screen

data class ProfileState(
    val userEmail: String? = null,
    val userBalance: Double? = null,
    val isLoading: Boolean = false
)