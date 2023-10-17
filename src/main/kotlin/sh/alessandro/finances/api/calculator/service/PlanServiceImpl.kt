package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.domain.repositories.PlanRepository
import sh.alessandro.finances.api.calculator.domain.service.ClientService
import sh.alessandro.finances.api.calculator.domain.service.PlanService
import sh.alessandro.finances.api.calculator.dto.request.EntryDataDto
import sh.alessandro.finances.api.security.domain.service.TokenService
import java.util.*

@Service
class PlanServiceImpl(
    private val planRepository: PlanRepository,
    private val clientService: ClientService,
    private val tokenService: TokenService
) : PlanService {
    override fun getMany(client: Client): List<Plan> {
        return planRepository.findByClient(client)
    }

    override fun getOne(id: UUID): Plan {
        return planRepository.findById(id).orElseThrow()
    }

    override fun saveOne(plan: Plan): Plan {
        return planRepository.save(plan)
    }

    override fun saveOne(payload: EntryDataDto, client: Client): Plan {
        val loan = payload.getLoan()

        val plan = Plan(postage = payload.postage)

        plan.loan = loan
        loan.plan = plan
        plan.insurances = payload.insurances.map { it.toInsurance(plan) }
        plan.client = client

        return planRepository.save(plan)
    }
}