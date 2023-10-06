package sh.alessandro.finances.calculator.repository

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import sh.alessandro.finances.calculator.domain.models.Loan
import sh.alessandro.finances.calculator.domain.persistence.LoanRepository
import java.util.*

@DataJpaTest
class LoanRepositoryTests {

    @Test
    fun testFindById() {
        val loanRepository = mock(LoanRepository::class.java)

        val loan = Loan(
            id = 1L,
            initialAmount = 20000.0,
            downPaymentPercentage = 10.0F,
            rate = 13.57F,
            term = 2u,
            date = Date()
        )

        `when`(loanRepository.findById(1L)).thenReturn(Optional.of(loan))

        val foundLoan = loanRepository.findById(1L)

        assertEquals(loan, foundLoan)
    }
}