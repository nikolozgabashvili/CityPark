package ge.tbca.city_park.domain.core.usecase

import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

class CheckSpecialCharUseCase @Inject constructor() {

    operator fun invoke(text: String): Boolean {
        val pattern = Pattern.compile(VALIDATION_REGEX_SPECIAL_CHAR)
        val hasSpecialChar: Matcher = pattern.matcher(text)
        return hasSpecialChar.find()
    }

    companion object {
        private const val VALIDATION_REGEX_SPECIAL_CHAR = "[!\"#\$%&'()*+,-./:;<=>?@\\[\\]^_`{|}~]"
    }
}