package sh.alessandro.finances.api.calculator.repository

import org.springframework.stereotype.Repository
import sh.alessandro.finances.api.calculator.domain.persistence.LoanRepository

@Repository
abstract class LoanRepositoryImpl : LoanRepository { }