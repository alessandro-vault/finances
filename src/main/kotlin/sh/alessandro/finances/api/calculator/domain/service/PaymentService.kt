package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.models.Plan
import java.util.*

interface PaymentService {
    fun getOne(id: UUID) : Payment
    fun createMany(plan: Plan): List<Payment>
    fun buildMany(plan: Plan) : List<Payment>
    fun buildFirstPayment(plan: Plan, postage: Double): Payment
}