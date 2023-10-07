package sh.alessandro.finances.api.calculator.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.domain.service.PlanService
import sh.alessandro.finances.api.calculator.dto.EntryDataDto
import java.util.*

@RestController
@RequestMapping("api/v1/plans")
class PlansController(
    private val planService: PlanService
) {

    @GetMapping("/{id}")
    fun getPlan(@PathVariable id: UUID): Plan {
        return planService.getOne(id)
    }

    @PostMapping()
    fun createPlan(@RequestBody payload: EntryDataDto): ResponseEntity<Map<String, Any>> {
        return try {
            val plan = planService.saveOneFromEntryData(payload)
            ResponseEntity(mapOf("plan" to plan), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to e), HttpStatus.CREATED)
        }
    }
}
