package sh.alessandro.finances.api.calculator.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.domain.repositories.ClientRepository
import sh.alessandro.finances.api.calculator.dto.request.RegisterClientDto
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.domain.service.HashService
import sh.alessandro.finances.api.security.domain.service.UserService
import sh.alessandro.finances.api.security.service.TokenServiceImpl
import java.util.*

class ClientServiceTests {
    @Test
    fun create() {

        val userService = mockk<UserService>()
        val clientRepository = mockk<ClientRepository>()
        val hashService = mockk<HashService>()
        val tokenService = mockk<TokenServiceImpl>()
        val clientService = ClientServiceImpl(clientRepository, userService, hashService, tokenService)
        val clientDto = RegisterClientDto("testuser", "testpassword", "John", "Doe")

        val userId = UUID.randomUUID()
        val savedUser = User(
            id = userId, // Set an appropriate ID for your test
            username = clientDto.username,
            password = clientDto.password
        )

        val savedClient = Client(
            id = 1L, // Set an appropriate ID for your test
            firstName = clientDto.firstName,
            lastName = clientDto.lastName,
            user = savedUser
        )

        // Arrange
        every { userService.existsByUsername(any(String::class)) } returns false
        every { userService.saveOne(any(User::class)) } returns savedUser
        every { clientRepository.save(any(Client::class)) } returns savedClient
        every { hashService.hashBcrypt(any(String::class)) } returns "hashedPassword"

        // Act
        val createdClient = clientService.register(clientDto)

        verify { userService.saveOne(any(User::class)) }
        verify { clientRepository.save(any(Client::class)) }

        // Assert
        assertEquals(savedClient, createdClient)
    }
}