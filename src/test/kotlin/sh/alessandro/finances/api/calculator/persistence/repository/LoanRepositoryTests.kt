package sh.alessandro.finances.api.calculator.persistence.repository

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sh.alessandro.finances.api.calculator.domain.models.Loan
import sh.alessandro.finances.api.calculator.domain.repositories.LoanRepository
import java.time.LocalDate
import java.util.*

class LoanRepositoryTests {

    @Test
    fun testFindById() {
        val loanRepository = mockk<LoanRepository>()

        val loan = Loan(
            id = 1L,
            totalAmount = 20000.0,
            downPaymentPercentage = 10.0F,
            rate = 13.57,
            term = 2u,
            date = LocalDate.now(),
        )

        every { loanRepository.findById(1L) } returns Optional.of(loan)

        val foundLoan = loanRepository.findById(1L).orElse(null)

        assertEquals(loan, foundLoan)
    }
}