package ge.tbca.city_park.domain.core.usecase

import javax.inject.Inject

class ValidateFieldUseCase @Inject constructor() {

    operator fun invoke(text: String): Boolean {
        return text.isNotEmpty()
    }
}