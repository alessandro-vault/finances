package sh.alessandro.finances.api.calculator.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sh.alessandro.finances.api.calculator.domain.models.service.PlanService
import sh.alessandro.finances.api.calculator.dto.EntryDataDto

@RestController
@RequestMapping("api/v1/plans")
class PlansController(
    private val planService: PlanService
) {

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