package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.domain.repositories.ClientRepository
import sh.alessandro.finances.api.calculator.domain.service.ClientService
import sh.alessandro.finances.api.calculator.dto.request.RegisterClientDto
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.domain.service.HashService
import sh.alessandro.finances.api.security.domain.service.UserService
import sh.alessandro.finances.api.security.dto.ApiException
import sh.alessandro.finances.api.security.dto.LoginResponseDto
import sh.alessandro.finances.api.security.service.TokenServiceImpl

@Service
class ClientServiceImpl(
    val clientRepository: ClientRepository,
    val userService: UserService,
    val hashService: HashService,
    val tokenService: TokenServiceImpl
) : ClientService {
    override fun findById(id: Long): Client? {
        return clientRepository.findById(id).orElseThrow()
    }

    override fun findByUsername(username: String): Client? {
        return clientRepository.findByUserUsername(username)
    }

    override fun findByToken(token: String): Client? {
        return clientRepository.findByUserUsername(
            tokenService.parseToken(token.replace("Bearer ", ""))!!.username
        )
    }

    override fun login(username: String, password: String): LoginResponseDto {
        val client = clientRepository.findByUserUsername(username) ?: throw ApiException(400, "Login failed")

        if (!hashService.checkBcrypt(password, client.user.password)) {
            throw ApiException(400, "Login failed")
        }

        return LoginResponseDto(
            token = tokenService.createToken(client.user)
        )
    }


    override fun register(client: RegisterClientDto): Client {
        if (userService.existsByUsername(client.username)) {
            throw ApiException(400, "Username already exists")
        }
        val user = userService.saveOne(
            User(
                username = client.username,
                password = hashService.hashBcrypt(client.password)
            )
        )

        return clientRepository.save(
            Client(
                firstName = client.firstName,
                lastName = client.lastName,
                user = user
            )
        )
    }
}