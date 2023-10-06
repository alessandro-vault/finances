package sh.alessandro.finances.api.calculator.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.util.*

class LoanRepositoryTests {

    @Test
    fun testFindById() {
        val loanRepository = mock(sh.alessandro.finances.api.calculator.domain.persistence.LoanRepository::class.java)

        val loan = sh.alessandro.finances.api.calculator.domain.models.Loan(
            id = 1L,
            initialAmount = 20000.0,
            downPaymentPercentage = 10.0F,
            rate = 13.57F,
            term = 2u,
            date = Date(),
        )

        `when`(loanRepository.findById(1L)).thenReturn(Optional.of(loan))

        val foundLoan = loanRepository.findById(1L).orElse(null)

        assertEquals(loan, foundLoan)
    }
}