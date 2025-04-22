package ge.tbca.city_park.payment.domain.usecase

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class ValidateCardExpireDateUseCase @Inject constructor() {

    operator fun invoke(expireDate: String): Boolean {
        val regex = Regex(VALIDATION_REGEX_EXPIRE_DATE)
        if (!regex.matches(expireDate)) return false

        val (mm, yy) = expireDate.split("/").map { it.toInt() }

        val currentMillis = Date(System.currentTimeMillis())
        val currentMonth = SimpleDateFormat("MM", Locale.getDefault()).format(currentMillis).toInt()
        val currentYear = SimpleDateFormat("yy", Locale.getDefault()).format(currentMillis).toInt()

        return when {
            yy > currentYear -> true
            yy == currentYear && mm >= currentMonth -> true
            else -> false
        }
    }

    companion object {
        private const val VALIDATION_REGEX_EXPIRE_DATE = "^(0[1-9]|1[0-2])/\\d{2}$"
    }
}
