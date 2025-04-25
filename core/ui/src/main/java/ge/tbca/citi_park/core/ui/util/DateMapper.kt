package ge.tbca.citi_park.core.ui.util

import java.text.SimpleDateFormat
import java.util.Locale

object DateMapper {

    fun millisToDate(millis: Long): String {
        val format = SimpleDateFormat("HH:mm, dd.MM.yy", Locale.getDefault())
        return format.format(millis)
    }
}