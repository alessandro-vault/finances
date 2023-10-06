package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Loan
import sh.alessandro.finances.api.calculator.domain.models.service.LoanService
import sh.alessandro.finances.api.calculator.domain.persistence.LoanRepository
import sh.alessandro.finances.api.calculator.dto.EntryDataDto
import java.text.SimpleDateFormat
import java.util.*

@Service
class LoanServiceImpl(
    val loanRepository: LoanRepository
) : LoanService {
    override fun saveOne(loan: Loan): Loan {
        return loanRepository.save(loan)
    }

    companion object {
        fun buildLoanFromEntryData(data: EntryDataDto) : Loan {
            return Loan(
                initialAmount = data.loanAmount,
                term = data.loanTerm.toUShort(),
                downPaymentPercentage = data.downPaymentPercentage.toFloat(),
                rate = data.interestRate.toFloat(),
                currency = data.currency,
                date = convertDate(data.loanDate)
            )
        }
        private fun convertDate(date: String): Date {
            val formatter = SimpleDateFormat("yyyy-MM-dd")
            return formatter.parse(date)
        }
    }

}