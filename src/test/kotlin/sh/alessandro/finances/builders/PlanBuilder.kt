package sh.alessandro.finances.builders

import sh.alessandro.finances.api.calculator.domain.enums.Currency
import sh.alessandro.finances.api.calculator.domain.enums.InsuranceType
import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.domain.models.Plan
import java.time.LocalDate

data class PlanBuilder(
    val totalAmount: Double = 20000.00,
    val downPaymentPercentage: Float = 10.0f,
    val rate: Double = 0.1345,
    val currency: Currency = Currency.PEN,
    val term: UShort = 24u,
    val date: LocalDate = LocalDate.of(2023, 9, 22),
    val postage: Double = 5.0,
    val lifeInsurancePercentage: Double = 0.00015,
    val carInsurancePercentage: Double = 0.0010,
    val client: Client? = null
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
            postage = postage,
            client = client
        )
    }
}