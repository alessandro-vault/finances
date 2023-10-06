package sh.alessandro.finances.api.calculator.domain.models.service

import java.util.*

interface PlanService {
    fun getOne(id: UUID): sh.alessandro.finances.api.calculator.domain.models.Plan
    fun saveOne(plan: sh.alessandro.finances.api.calculator.domain.models.Plan): sh.alessandro.finances.api.calculator.domain.models.Plan
}