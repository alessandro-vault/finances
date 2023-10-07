package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.models.Plan

interface CalculatorService {
    fun calcAmortization(plan: Plan) : Double
    fun calcInterest(plan: Plan) : Double
    fun calcLifeInsurance(plan: Plan) : Double
    fun calcCarInsurance(plan: Plan) : Double
    fun calcBalance(plan: Plan) : Double

    fun buildFirstPayment(plan: Plan) : Payment
}