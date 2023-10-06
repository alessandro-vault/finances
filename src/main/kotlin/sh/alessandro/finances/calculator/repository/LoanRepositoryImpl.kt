package sh.alessandro.finances.calculator.repository

import org.springframework.stereotype.Repository
import sh.alessandro.finances.calculator.domain.models.Loan
import sh.alessandro.finances.calculator.domain.persistence.LoanRepository

@Repository
abstract class LoanRepositoryImpl : LoanRepository { }