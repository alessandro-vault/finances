package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.domain.repositories.PaymentRepository
import sh.alessandro.finances.api.calculator.domain.service.PaymentService
import java.util.*

@Service
class PaymentServiceImpl(
    private val paymentRepository: PaymentRepository,
) : PaymentService {
    override fun getOne(id: UUID): Payment =
        paymentRepository.findById(id).orElseThrow()

    override fun getFromPlan(plan: Plan): List<Payment> =
        paymentRepository.getAllByPlan(plan).sortedBy { it.number }

        override fun createMany(plan: Plan): List<Payment> {
        val payments = this.buildMany(plan)
        return paymentRepository.saveAll(payments)
    }

    override fun buildMany(plan: Plan) : List<Payment> {
        val periods = plan.loan?.term?.toInt()!!
        val payments = mutableListOf<Payment>()

        // The first payment is calculated from the initial balance
        payments.add(this.buildFirstPayment(plan, plan.postage))

        for (i in 2..periods) {
            val prevPayment : Payment = payments.last()
            val interest : Double = prevPayment.balance * plan.monthlyRate()
            val lifeInsurance : Double = plan.lifeInsurance() * prevPayment.balance
            val carInsurance : Double = plan.carInsurance() * plan.loan!!.totalAmount

            val amortization : Double = plan.monthlyPayment() - interest - lifeInsurance - carInsurance - plan.postage
            var balance : Double = prevPayment.balance - amortization

            // if the remaining balance is less than 0.001, it is considered 0
            if (balance < 0.001) balance = 0.0

            payments.add(
                Payment(
                    null,
                    i,
                    prevPayment.paymentDate.plusMonths(1),
                    amortization,
                    interest,
                    lifeInsurance,
                    carInsurance,
                    plan.postage,
                    balance,
                    plan = plan
                )
            )
        }
        return payments
    }
    override fun buildFirstPayment(plan: Plan, postage: Double): Payment {
        val loan = plan.loan!!
        val interest = loan.totalDebt() * plan.monthlyRate()
        val lifeInsurance = plan.lifeInsurance() * loan.totalDebt()
        val carInsurance = plan.carInsurance() * loan.totalAmount

        val amortization = plan.monthlyPayment() - interest - lifeInsurance - carInsurance - plan.postage
        val balance = loan.totalDebt() - amortization

        return Payment(
            null,
            1,
            loan.date.plusMonths(1),
            amortization,
            interest,
            lifeInsurance,
            carInsurance,
            plan.postage,
            balance,
            plan = plan
        )
    }
}