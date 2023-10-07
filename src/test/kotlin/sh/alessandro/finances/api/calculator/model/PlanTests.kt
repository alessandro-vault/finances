package sh.alessandro.finances.api.calculator.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.TestComponent
import sh.alessandro.finances.api.calculator.domain.enums.Currency
import sh.alessandro.finances.api.calculator.domain.enums.InsuranceType
import sh.alessandro.finances.api.calculator.domain.models.Insurance
import sh.alessandro.finances.api.calculator.domain.models.Loan
import sh.alessandro.finances.api.calculator.domain.models.Plan
import java.time.LocalDate

@TestComponent
class PlanTests {
    @Test
    fun `should calculate monthly payment`() {
        // Given
        val plan = Plan(
            loan = Loan(
                totalAmount = 20000.00,
                downPaymentPercentage = 10.0f,
                rate = 0.1357,
                currency = Currency.PEN,
                term = 24u,
                date = LocalDate.of(2023, 9, 22)
            ),
            insurances = listOf(
                Insurance(
                    type = InsuranceType.LIFE,
                    percentage = 0.00015
                ),
                Insurance(
                    type = InsuranceType.CAR,
                    percentage = 0.0010
                )
            ),
            postage = 5.0
        )

        // When
        val monthlyPayment = plan.monthlyPayment()

        // Then
        assertEquals(880.52, monthlyPayment, 0.01)
    }
}