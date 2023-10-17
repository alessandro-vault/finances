package sh.alessandro.finances.api.calculator.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.service.PaymentService
import java.util.*

@RestController
@RequestMapping("api/v1/payments")
class PaymentsController(
    val paymentService: PaymentService
) {
    @GetMapping("/{id}")
    fun getOne(@PathVariable id: UUID): ResponseEntity<Payment> {
        return try {
            val payment = paymentService.getOne(id)
            ResponseEntity(payment, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}