package ge.tbca.city_park.reservation.domain.usecase

import javax.inject.Inject

class ValidateZoneCodeUseCase @Inject constructor() {

    operator fun invoke(zoneCode: String): Boolean {
        return zoneCode.length == 5 && zoneCode.take(2).all { it.isLetter() } &&
                zoneCode.takeLast(3).all { it.isDigit() }
    }
}