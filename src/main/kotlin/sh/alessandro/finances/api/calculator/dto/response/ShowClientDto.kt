package sh.alessandro.finances.api.calculator.dto.response

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import sh.alessandro.finances.api.calculator.domain.models.Client

@Data
@NoArgsConstructor
@AllArgsConstructor
class ShowClientDto(private val client: Client) {
    val clientId = client.id
    val userId = client.user.id
    val firstName = client.firstName
    val lastName = client.lastName
    val username = client.user.username
    val memberSince = client.user.createdAt
}
