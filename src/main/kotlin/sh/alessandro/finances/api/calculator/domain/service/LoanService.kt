package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Loan

interface LoanService {
    fun getOne(id: Long): Loan?
    fun saveOne(loan: Loan): Loan
}