package ge.tbca.city_park.home.presentation.screen

sealed interface HomeEffect {
    data object NavigateToAddCar : HomeEffect
}