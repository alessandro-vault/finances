package sh.alessandro.finances.api.calculator.domain.models.service

import sh.alessandro.finances.api.calculator.domain.models.Loan

interface LoanService {
    fun saveOne(loan: Loan): Loan
}