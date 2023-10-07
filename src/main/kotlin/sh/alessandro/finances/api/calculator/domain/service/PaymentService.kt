package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.models.Plan

interface PaymentService {
    fun createPayments(plan: Plan): List<Payment>
}