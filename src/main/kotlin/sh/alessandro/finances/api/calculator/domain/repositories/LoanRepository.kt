package sh.alessandro.finances.api.calculator.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository

interface LoanRepository : JpaRepository<sh.alessandro.finances.api.calculator.domain.models.Loan, Long>