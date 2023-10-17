package sh.alessandro.finances.builders

import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.domain.models.Plan
import sh.alessandro.finances.api.security.domain.models.User
import java.util.*

data class ClientBuilder(
    var firstName: String = "John",
    var lastName: String = "Doe",
    var username: String = "testuser",
    var password: String = "testpassword",
) {
    fun build() = Client(
        id = 1L,
        firstName = firstName,
        lastName = lastName,
        user = User(
            id = UUID.randomUUID(),
            username = username,
            password = password),
        plans = listOf<Plan>()
    )
}