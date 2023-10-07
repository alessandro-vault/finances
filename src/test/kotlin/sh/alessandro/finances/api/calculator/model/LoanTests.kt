package sh.alessandro.finances.api.calculator.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.TestComponent
import sh.alessandro.finances.api.calculator.domain.models.Loan
import java.util.*

@TestComponent
class LoanTests {

    private lateinit var loan : Loan

    @BeforeEach
    fun setUp() {
        loan = Loan(
            id = 1L,
            initialAmount = 20000.0,
            downPaymentPercentage = 10.0F,
            rate = 13.57F,
            term = 2u,
            date = Date()
        )
    }

    @Test
    fun testCalculateDownPayment() {
        assertEquals(2000.0, loan.downPayment())
    }
}