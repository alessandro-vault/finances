package sh.alessandro.finances.api.calculator.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor


@NoArgsConstructor
@AllArgsConstructor
@Data
data class EntryDataDto(
    @JsonProperty("loanAmount")
    val loanAmount: Double,
    @JsonProperty("downPaymentPercentage")
    val downPaymentPercentage: Double,
    @JsonProperty("interestRate")
    val interestRate: Double,
)