package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import java.util.*

@Service
class PlanServiceImpl(
    private val planRepository: sh.alessandro.finances.api.calculator.domain.persistence.PlanRepository
) : sh.alessandro.finances.api.calculator.domain.models.service.PlanService {
    override fun getOne(id: UUID): sh.alessandro.finances.api.calculator.domain.models.Plan {
        return planRepository.findById(id).orElseThrow()
    }

    override fun saveOne(plan: sh.alessandro.finances.api.calculator.domain.models.Plan): sh.alessandro.finances.api.calculator.domain.models.Plan {
        return planRepository.save(plan)
    }
}