package sh.alessandro.finances.api.calculator.dto

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import sh.alessandro.finances.api.calculator.domain.enums.Currency
import sh.alessandro.finances.api.calculator.domain.enums.RateType


@NoArgsConstructor
@AllArgsConstructor
@Data
data class EntryDataDto(
    @JsonProperty("loanAmount")
    @NotNull
    val loanAmount: Double,

    @JsonProperty("downPaymentPercentage")
    @NotNull
    val downPaymentPercentage: Double,

    @JsonProperty("interestRate")
    @NotNull
    val interestRate: Double,

    @JsonProperty("loanTerm")
    @NotNull
    val loanTerm: Int,

    @JsonProperty("rateType")
    @NotNull
    val rateType: RateType,

    @JsonProperty("currency")
    @NotNull
    val currency: Currency,

    @JsonProperty("portage")
    @NotNull
    val postage: Double,

    @JsonProperty("loanDate")
    @NotNull
    val loanDate: String
)