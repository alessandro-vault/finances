package sh.alessandro.finances.api.calculator.persistence.repository

import org.springframework.stereotype.Repository
import sh.alessandro.finances.api.calculator.domain.repositories.LoanRepository

@Repository
abstract class LoanRepositoryImpl : LoanRepository { }