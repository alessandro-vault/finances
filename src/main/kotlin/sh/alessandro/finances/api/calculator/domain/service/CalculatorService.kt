package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Plan

interface CalculatorService {
    fun calcAmortization(plan: Plan) : Double
    fun calcInterest(balance: Double, monthlyRate: Double) : Double
    fun calcInsurance(plan: Plan) : Double
    fun calcBalance(plan: Plan) : Double
}