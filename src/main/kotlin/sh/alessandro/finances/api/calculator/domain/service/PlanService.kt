package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.dto.request.EntryDataDto
import java.util.*

interface PlanService {
    fun getOne(id: UUID): Plan
    fun saveOne(plan: Plan): Plan
    fun saveOne(payload: EntryDataDto, client: Client): Plan
}