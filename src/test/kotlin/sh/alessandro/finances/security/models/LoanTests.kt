package sh.alessandro.finances.security.models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import sh.alessandro.finances.calculator.domain.models.Loan
import java.util.*

@DataJpaTest
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