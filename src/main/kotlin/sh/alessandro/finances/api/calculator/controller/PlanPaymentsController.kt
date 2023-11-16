package sh.alessandro.finances.api.calculator.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.service.PaymentService
import sh.alessandro.finances.api.calculator.domain.service.PlanService
import java.util.*

@RestController
@RequestMapping("api/v1/plans/{planId}/payments")
class PlanPaymentsController(
    val planService: PlanService,
    val paymentService: PaymentService
) {
    @GetMapping()
    fun getPlanPayments(@PathVariable planId: UUID): ResponseEntity<List<Payment>> {
        return try {
            val plan = planService.getOne(planId)
            val payments = paymentService.getFromPlan(plan)
            ResponseEntity(payments, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
    @PostMapping()
    fun createPlanPayments(@PathVariable planId: UUID): ResponseEntity<Map<String, Any>> {
        return try {
            val plan = planService.getOne(planId)
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