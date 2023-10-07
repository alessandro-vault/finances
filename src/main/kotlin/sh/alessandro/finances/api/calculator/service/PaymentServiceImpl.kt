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

        payments.add(this.buildFirstPayment(plan, plan.postage))

        return payments
    }

    fun getPrevPayment(plan: Plan, index: Int): Payment? {
        return plan.payments.lastOrNull()
    }

    fun buildFirstPayment(plan: Plan, postage: Double): Payment {
        val loan = plan.loan!!
        val interest = calculator.calcInterest(
            loan.totalDebt(),
            plan.monthlyRate()
        )

        return Payment(
            number = 1,
            // payment date will be plan.loan.date + 30 days
            paymentDate = loan.date,
            amortization = 0.0,
            interest = interest,
            lifeInsurance = 18000.00 * 0.015 / 100,
            carInsurance = loan.totalAmount * 0.10 / 100,
            postage = postage,
            balance = 0.0,
        )
    }

}