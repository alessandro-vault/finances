package sh.alessandro.finances.api.calculator.dto.request

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class NewClientDto(
    var firstName: String,
    var lastName: String,
    var username: String,
    var password: String,
)