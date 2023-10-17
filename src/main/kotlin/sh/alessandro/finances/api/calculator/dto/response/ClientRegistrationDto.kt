package sh.alessandro.finances.api.calculator.dto.response

import lombok.Data
import sh.alessandro.finances.api.calculator.domain.models.Client

@Data
class ClientRegistrationDto (private val client: Client) {
    val id = client.id
    val name = client.fullName()
    val username = client.user.username
    val createdAt: String = client.user.createdAt.toString()
}