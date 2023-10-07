package sh.alessandro.finances.api.calculator.shared

import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object {
        fun convertDate(date: String): Date {
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            return formatter.parse(date)
        }
    }
}