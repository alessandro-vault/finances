package sh.alessandro.finances.builders

import sh.alessandro.finances.api.calculator.domain.enums.Currency
import sh.alessandro.finances.api.calculator.domain.enums.InsuranceType
import sh.alessandro.finances.api.calculator.domain.models.Plan
import java.time.LocalDate

data class PlanBuilder(
    val totalAmount: Double,
    val downPaymentPercentage: Float,
    val rate: Double,
    val currency: Currency = Currency.PEN,
    val term: UShort,
    val date: LocalDate,
    val postage: Double,
    val lifeInsurancePercentage: Double,
    val carInsurancePercentage: Double
) {
    fun build() : Plan {
        return Plan(
            loan = LoanBuilder(
                totalAmount = totalAmount,
                downPaymentPercentage = downPaymentPercentage,
                rate = rate,
                currency = currency,
                term = term,
                date = date
            ).build(),
            insurances = listOf(
                InsuranceBuilder(
                    type = InsuranceType.LIFE,
                    percentage = lifeInsurancePercentage
                ).build(),
                InsuranceBuilder(
                    type = InsuranceType.CAR,
                    percentage = carInsurancePercentage
                ).build()
            ),
            postage = postage
        )
    }
}