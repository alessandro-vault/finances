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
    @ResponseStatus(HttpStatus.CREATED)
    fun createPlan(@RequestBody payload: EntryDataDto): ResponseEntity<Map<String, Any>> {
        val plan = planService.saveOneFromEntryData(payload)
        val response = mapOf(
            "message" to "Successfully created plan",
            "plan" to plan
        )
        return ResponseEntity(response, HttpStatus.CREATED)
    }
}