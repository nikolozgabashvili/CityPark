package ge.tbca.city_park.presentation.features.register.screen

sealed interface RegisterEffect {
    data object NavigateBack : RegisterEffect
    data object SignUpWithGoogle : RegisterEffect
}