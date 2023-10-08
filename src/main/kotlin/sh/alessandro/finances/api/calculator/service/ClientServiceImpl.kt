package sh.alessandro.finances.api.calculator.service

import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.domain.repositories.ClientRepository
import sh.alessandro.finances.api.calculator.domain.service.ClientService
import sh.alessandro.finances.api.calculator.dto.NewClientDto
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.domain.service.UserService

@Service
@AllArgsConstructor
@NoArgsConstructor
class ClientServiceImpl(
    val clientRepository: ClientRepository,
    val userService: UserService
) : ClientService {
    override fun findById(id: Long): Client? {
        return clientRepository.findById(id).orElseThrow()
    }

    override fun create(client: NewClientDto): Client {
        val user = userService.saveOne(
            User(username = client.username, password = client.password)
        )

        return clientRepository.save(Client(
            firstName = client.firstName,
            lastName = client.lastName,
            user = user
        ))
    }
}