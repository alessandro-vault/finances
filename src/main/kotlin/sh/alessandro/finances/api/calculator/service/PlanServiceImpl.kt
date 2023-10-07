package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.domain.repositories.PlanRepository
import sh.alessandro.finances.api.calculator.domain.service.LoanService
import sh.alessandro.finances.api.calculator.domain.service.PlanService
import sh.alessandro.finances.api.calculator.dto.EntryDataDto
import java.util.*

@Service
class PlanServiceImpl(
    private val planRepository: PlanRepository,
    private val loanService: LoanService
) : PlanService {
    override fun getOne(id: UUID): Plan {
        return planRepository.findById(id).orElseThrow()
    }

    override fun saveOne(plan: Plan): Plan {
        return planRepository.save(plan)
    }

    override fun saveOneFromEntryData(payload: EntryDataDto): Plan {
        val loan = loanService.saveOne(
            LoanServiceImpl.buildLoanFromEntryData(payload)
        )

        val plan = Plan(
            postage = payload.postage,
            loan = loan
        )
        saveOne(plan)

        loan.plan = plan
        return planRepository.save(plan)
    }
}