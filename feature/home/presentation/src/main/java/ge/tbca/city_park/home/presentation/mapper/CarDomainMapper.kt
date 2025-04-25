package ge.tbca.city_park.home.presentation.mapper

import ge.tbca.city_park.cars.domain.model.CarDomain
import ge.tbca.city_park.cars.presentation.model.CarUi

private fun CarDomain.toPresenter(): CarUi {
    return CarUi(
        id = id,
        carName = if (carName.isNullOrEmpty()) null else carName,
        plateNumber = plateNumber
    )
}

fun List<CarDomain>.toPresenter(): List<CarUi> {
    return map { it.toPresenter() }
}