package sh.alessandro.finances.api.calculator.dto.response

import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import sh.alessandro.finances.api.calculator.domain.models.Plan
import kotlin.math.pow

@Data
@NoArgsConstructor
@AllArgsConstructor
class ShowPlanDto(private val plan: Plan) {
    val id = plan.id
    val title = plan.title
    val payments = plan.payments
    @JsonIgnore
    val loanValue = plan.loan?.totalAmount
    val loan = plan.loan

    fun getStats() : Map<String, Any> {
        return mapOf(
            "totalAmount" to loanValue!!,
            "downPaymentPercentage" to "${plan.loan!!.downPaymentPercentage}%",
            "downPayment" to plan.loan!!.downPaymentAmount(),
            "remainingAmount" to plan.loan!!.totalDebt(),
            "APR" to plan.loan!!.rate * 100,
            "MPR" to plan.monthlyRate() * 100,
            "numberOfPayments" to plan.loan!!.term,
            "monthlyPayment" to plan.monthlyPayment(),
            "lifeInsurance" to plan.lifeInsurance(),
            "carInsurance" to plan.carInsurance(),
            "postage" to plan.postage,
            "IRR" to plan.irr(),
            "EAR" to ((1 + plan.irr()).pow(360.0 / 30) - 1),
            "test" to plan.lifeInsurance()
        )
    }
}
