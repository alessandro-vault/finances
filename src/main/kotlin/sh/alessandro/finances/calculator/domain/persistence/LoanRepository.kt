package sh.alessandro.finances.calculator.domain.persistence

import org.springframework.data.jpa.repository.JpaRepository
import sh.alessandro.finances.calculator.domain.models.Loan

interface LoanRepository : JpaRepository<Loan, Long>