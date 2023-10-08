package sh.alessandro.finances.api.calculator.service

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Loan
import sh.alessandro.finances.api.calculator.domain.repositories.LoanRepository
import sh.alessandro.finances.api.calculator.domain.service.LoanService

@Service
@AllArgsConstructor
@NoArgsConstructor
class LoanServiceImpl(
    val loanRepository: LoanRepository
) : LoanService {
    override fun getOne(id: Long): Loan? {
        return loanRepository.findById(id).orElseThrow()
    }
    override fun saveOne(loan: Loan): Loan {
        return loanRepository.save(loan)
    }
}