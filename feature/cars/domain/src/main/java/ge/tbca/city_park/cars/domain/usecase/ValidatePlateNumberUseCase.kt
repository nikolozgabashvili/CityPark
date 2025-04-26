package ge.tbca.city_park.cars.domain.usecase

import javax.inject.Inject

class ValidatePlateNumberUseCase @Inject constructor() {

    operator fun invoke(plateNumber: String): Boolean {
        return plateNumber.isNotEmpty()
    }
}