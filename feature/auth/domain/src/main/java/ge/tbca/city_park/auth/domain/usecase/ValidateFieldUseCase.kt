package ge.tbca.city_park.auth.domain.usecase

import javax.inject.Inject

class ValidateFieldUseCase @Inject constructor() {

    operator fun invoke(text: String): Boolean {
        return text.isNotEmpty()
    }
}