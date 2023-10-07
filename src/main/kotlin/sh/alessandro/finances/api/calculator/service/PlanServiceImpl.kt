package sh.alessandro.finances.api.calculator.service

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.domain.repositories.PlanRepository
import sh.alessandro.finances.api.calculator.domain.service.InsuranceService
import sh.alessandro.finances.api.calculator.domain.service.LoanService
import sh.alessandro.finances.api.calculator.domain.service.PlanService
import sh.alessandro.finances.api.calculator.dto.EntryDataDto
import java.util.*

@Service
@AllArgsConstructor
@NoArgsConstructor
class PlanServiceImpl(
    private val planRepository: PlanRepository,
    private val loanService: LoanService,
    private val insuranceService: InsuranceService
) : PlanService {
    override fun getOne(id: UUID): Plan {
        return planRepository.findById(id).orElseThrow()
    }

    override fun saveOne(plan: Plan): Plan {
        return planRepository.save(plan)
    }

    override fun saveOneFromEntryData(payload: EntryDataDto): Plan {
        val loan = payload.getLoan()

        val plan = Plan(postage = payload.postage)

        plan.loan = loan
        loan.plan = plan

        plan.insurances = payload.insurances.map { it.toInsurance(plan) }

        return planRepository.save(plan)
    }
}