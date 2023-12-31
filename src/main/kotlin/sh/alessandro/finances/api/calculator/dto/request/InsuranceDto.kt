package sh.alessandro.finances.api.calculator.dto.request

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import sh.alessandro.finances.api.calculator.domain.enums.InsuranceType
import sh.alessandro.finances.api.calculator.domain.models.Insurance
import sh.alessandro.finances.api.calculator.domain.models.Plan

@Data
@NoArgsConstructor
@AllArgsConstructor
data class InsuranceDto(
    val type: InsuranceType,
    val percentage: Double? = null,
) {
    fun toInsurance(plan: Plan? = null) : Insurance {
        return Insurance(
            percentage = this.percentage ?: 0.0,
            type = this.type,
            plan = plan
        )
    }
}