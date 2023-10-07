package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.domain.service.CalculatorService

@Service
class CalculatorServiceImpl : CalculatorService {
    override fun calcAmortization(plan: Plan): Double {
        TODO("Not yet implemented")
    }

    override fun calcInterest(plan: Plan): Double {
        TODO("Not yet implemented")
    }

    override fun calcLifeInsurance(plan: Plan): Double {
        TODO("Not yet implemented")
    }

    override fun calcCarInsurance(plan: Plan): Double {
        TODO("Not yet implemented")
    }

    override fun calcBalance(plan: Plan): Double {
        TODO("Not yet implemented")
    }


    override fun buildFirstPayment(plan: Plan): Payment {
        val loan = plan.loan!!
        val interest = loan.loanAmount() * loan.monthlyRate() / 100
        return Payment(
            number = 1,
            // payment date will be plan.loan.date + 30 days
            paymentDate = plan.loan?.date!!,
            amortization = calcAmortization(plan),
            interest = calcInterest(plan),
            lifeInsurance = calcLifeInsurance(plan),
            carInsurance = calcCarInsurance(plan),
            postage = plan.postage,
            balance = calcBalance(plan),
        )
    }
}