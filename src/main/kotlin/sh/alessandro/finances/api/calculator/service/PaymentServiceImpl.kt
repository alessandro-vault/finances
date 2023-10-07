package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.domain.service.CalculatorService
import sh.alessandro.finances.api.calculator.domain.service.PaymentService

@Service
class PaymentServiceImpl(
    private val calculator: CalculatorService
) : PaymentService {
    override fun createPayments(plan: Plan): List<Payment> {
        var periods = plan.loan?.term?.toInt()
        var payments = mutableListOf<Payment>()

        payments.add(calculator.buildFirstPayment(plan))

        return payments
    }

    fun getPrevPayment(plan: Plan, index: Int): Payment? {
        return plan.payments?.lastOrNull()
    }

}