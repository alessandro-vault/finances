package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.calculator.domain.service.CalculatorService

@Service
class CalculatorServiceImpl : CalculatorService {
    override fun calcAmortization(plan: Plan): Double {
        TODO("Not yet implemented")
    }

    override fun calcInterest(balance: Double, monthlyRate: Double): Double {
        return balance * monthlyRate / 100
    }

    override fun calcInsurance(plan: Plan): Double {
        TODO("Not yet implemented")
    }

    override fun calcBalance(plan: Plan): Double {
        TODO("Not yet implemented")
    }
}