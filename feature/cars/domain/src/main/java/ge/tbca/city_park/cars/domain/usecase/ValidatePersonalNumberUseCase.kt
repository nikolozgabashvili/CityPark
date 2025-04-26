package ge.tbca.city_park.cars.domain.usecase

import javax.inject.Inject

class ValidatePersonalNumberUseCase @Inject constructor() {

    operator fun invoke(personalNumber: String): Boolean {
        val trimmed = personalNumber.trim()
        return trimmed.length == 11 && personalNumber.all { it.isDigit() }
    }
}