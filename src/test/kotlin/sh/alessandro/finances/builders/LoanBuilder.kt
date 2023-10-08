package sh.alessandro.finances.builders

import sh.alessandro.finances.api.calculator.domain.enums.Currency
import sh.alessandro.finances.api.calculator.domain.models.Loan
import java.time.LocalDate

data class LoanBuilder(
    var totalAmount: Double,
    var downPaymentPercentage: Float,
    var rate: Double,
    var currency: Currency = Currency.PEN,
    var term: UShort,
    var date: LocalDate
) {
    fun build() : Loan {
        return Loan(
            totalAmount = totalAmount,
            downPaymentPercentage = downPaymentPercentage,
            rate = rate,
            currency = currency,
            term = term,
            date = date
        )
    }
}