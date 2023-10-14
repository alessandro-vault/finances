package sh.alessandro.finances.api.calculator.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sh.alessandro.finances.api.calculator.domain.service.PaymentService
import sh.alessandro.finances.api.calculator.domain.service.PlanService
import sh.alessandro.finances.api.calculator.dto.EntryDataDto
import sh.alessandro.finances.api.calculator.dto.ShowPlanDto
import java.util.*

@RestController
@RequestMapping("api/v1/plans")
class PlansController(
    private val planService: PlanService,
    private val paymentService: PaymentService
) {
    @GetMapping("/{id}")
    fun getPlan(@PathVariable id: UUID): ResponseEntity<Map<String, ShowPlanDto>> {
        return try {
            val plan = planService.getOne(id)
            println(plan.payments.size)
            ResponseEntity(
                mapOf("plan" to ShowPlanDto(plan))
                , HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/{id}/stats")
    fun getPlanStats(@PathVariable id: UUID): ResponseEntity<Map<String, Any>> {
        return try {
            ResponseEntity(
                mapOf("stats for plan" to ShowPlanDto(planService.getOne(id)).getStats()),
                HttpStatus.OK
            )
        } catch (e: Exception) {
            ResponseEntity(mapOf("message" to "resource not found"), HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping()
    fun createPlan(@RequestBody payload: EntryDataDto): ResponseEntity<Map<String, Any>> {
        return try {
            val plan = planService.saveOne(payload)
            ResponseEntity(mapOf("plan" to plan), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to e), HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    @PostMapping("/{id}/payments")
    fun createPayments(@PathVariable id: UUID): ResponseEntity<Map<String, Any>> {
        return try {
            val plan = planService.getOne(id)
            if (plan.payments.isNotEmpty()) {
                throw Exception("Payments are already created")
            }
            val payments = paymentService.createMany(plan)
            ResponseEntity(mapOf("payments" to payments), HttpStatus.CREATED)
        } catch (e: Exception) {
            ResponseEntity(mapOf("error" to e.message!!), HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }
}
