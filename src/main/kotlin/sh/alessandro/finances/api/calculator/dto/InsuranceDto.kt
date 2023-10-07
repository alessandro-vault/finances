package sh.alessandro.finances.api.calculator.dto

import sh.alessandro.finances.api.calculator.domain.enums.InsuranceType
import sh.alessandro.finances.api.calculator.domain.models.Insurance
import sh.alessandro.finances.api.calculator.domain.models.Plan

data class InsuranceDto(
    val type: InsuranceType,
    val percentage: Double? = null,
) {
    fun toInsurance(plan: Plan? = null) : Insurance {
        return Insurance(
            percentage = this.percentage,
            type = this.type,
            plan = plan
        )
    }
}