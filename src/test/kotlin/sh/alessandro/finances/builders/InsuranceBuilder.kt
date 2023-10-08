package sh.alessandro.finances.builders

import sh.alessandro.finances.api.calculator.domain.enums.InsuranceType
import sh.alessandro.finances.api.calculator.domain.models.Insurance

data class InsuranceBuilder(
    var type: InsuranceType,
    var percentage: Double
) {
    fun build() : Insurance {
        return Insurance(
            type = type,
            percentage = percentage
        )
    }
}