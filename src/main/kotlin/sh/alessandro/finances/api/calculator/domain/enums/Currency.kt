package sh.alessandro.finances.api.calculator.domain.enums

import com.fasterxml.jackson.annotation.JsonProperty

enum class Currency {
    @JsonProperty("PEN") PEN,
    USD,
    MXN
}