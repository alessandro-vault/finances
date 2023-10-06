package sh.alessandro.finances.api.calculator.domain.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface LoanRepository : JpaRepository<sh.alessandro.finances.api.calculator.domain.models.Loan, Long>