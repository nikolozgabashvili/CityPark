package ge.tbca.city_park.payment.domain.usecase

import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class ValidateCardExpireDateUseCase @Inject constructor() {

    operator fun invoke(expireDate: String): Boolean {
        if (expireDate.length != 4) return false
        if (expireDate.any { !it.isDigit() }) return false

        val (mm, yy) = expireDate.substring(0, 2).toInt() to expireDate.substring(2, 4).toInt()


        if (mm !in 1..12) return false

        val currentDate =System.currentTimeMillis()
        val currentMonth = SimpleDateFormat("MM", Locale.getDefault()).format(currentDate).toInt()
        val currentYear = SimpleDateFormat("yy", Locale.getDefault()).format(currentDate).toInt()

        return yy > currentYear || (yy == currentYear && mm >= currentMonth)
    }


}
