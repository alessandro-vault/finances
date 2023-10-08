package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.dto.EntryDataDto
import java.util.*

interface PlanService {
    fun getOne(id: UUID): Plan
    fun saveOne(plan: Plan): Plan
    fun saveOne(payload: EntryDataDto): Plan
}