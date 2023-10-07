package sh.alessandro.finances.api.calculator.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.TestComponent
import sh.alessandro.finances.api.calculator.domain.models.Loan
import java.time.LocalDate

@TestComponent
class LoanTests {

    private lateinit var loan : Loan

    @BeforeEach
    fun setUp() {
        loan = Loan(
            id = 1L,
            totalAmount = 20000.0,
            downPaymentPercentage = 10.0F,
            rate = 13.57,
            term = 2u,
            date = LocalDate.now()
        )
    }

    @Test
    fun testCalculateDownPayment() {
        assertEquals(18000.00, loan.totalDebt())
    }
}
