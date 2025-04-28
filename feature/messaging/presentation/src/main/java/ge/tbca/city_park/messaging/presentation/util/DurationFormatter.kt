package ge.tbca.city_park.messaging.presentation.util

import java.util.Locale
import kotlin.time.Duration

fun Duration.formatted(): String {
    val locale = Locale.US
    val totalSeconds = inWholeSeconds
    val hours = String.format(locale, "%02d", totalSeconds / (60 * 60))
    val minutes = String.format(locale, "%02d", (totalSeconds % (3600)) / 60)
    val seconds = String.format(locale, "%02d", (totalSeconds % 60))

    return "$hours:$minutes:$seconds"
}