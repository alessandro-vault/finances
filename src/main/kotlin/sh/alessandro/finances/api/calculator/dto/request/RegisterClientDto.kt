package sh.alessandro.finances.api.calculator.dto.request

import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull

data class RegisterClientDto(
    @NotNull
    @NotBlank
    val firstName: String,
    val lastName: String? = "",
    @NotNull
    @NotBlank
    val username: String,
    @NotNull
    @NotBlank
    val password: String
)