package ge.tbca.city_park.cars.domain.usecase

import javax.inject.Inject

class ValidateCarNameUseCase @Inject constructor() {

    operator fun invoke(carName: String): Boolean {
        val trimmed = carName.trim()
        return trimmed.isNotEmpty() && trimmed.length <= 20
    }
}