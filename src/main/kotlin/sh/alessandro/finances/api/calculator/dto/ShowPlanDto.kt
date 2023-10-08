package sh.alessandro.finances.api.calculator.dto

import sh.alessandro.finances.api.calculator.domain.models.Plan
import kotlin.math.pow

class ShowPlanDto(val plan: Plan) {
    val id = plan.id
    private val loanValue = plan.loan?.totalAmount
    val downPayment = plan.loan!!.downPaymentPercentage
    val payments = plan.payments

    fun getStats() : Map<String, Any> {
        return mapOf(
            "id" to id!!,
            "totalAmount" to loanValue!!,
            "downPaymentPercentage" to "${plan.loan!!.downPaymentPercentage}%",
            "downPayment" to plan.loan!!.downPaymentAmount(),
            "remainingAmount" to plan.loan!!.totalDebt(),
            "APR" to "${plan.loan!!.rate * 100}%",
            "MPR" to "${plan.monthlyRate() * 100}%",
            "numberOfPayments" to plan.loan!!.term,
            "monthlyPayment" to plan.monthlyPayment(),
            "lifeInsurance" to plan.lifeInsurance(),
            "carInsurance" to plan.carInsurance(),
            "postage" to plan.postage,
            "IRR" to plan.irr(),
            "EAR" to ((1 + plan.irr()).pow(360.0 / 30) - 1)
        )
    }
}