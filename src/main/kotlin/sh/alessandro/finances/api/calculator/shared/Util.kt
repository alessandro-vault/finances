package sh.alessandro.finances.api.calculator.shared

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class Util {
    companion object {
        fun convertDate(date: String): LocalDate {
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            return formatter.parse(date).toInstant().atZone(TimeZone.getTimeZone("UTC").toZoneId()).toLocalDate()
        }
    }
}