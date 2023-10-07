package sh.alessandro.finances.api.security.repository

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.domain.persistence.UserRepository
import java.util.*

class UserRepositoryTests {

    @Test
    fun testFindByUsername() {
        val userRepository = mockk<UserRepository>()

        val username = "testUser"
        val user = User(id = UUID.randomUUID(), username = username, password = "testPassword")

        every { userRepository.findByUsername(username) } returns user

        val result = userRepository.findByUsername(username)

        assertNotNull(result)
        assertEquals(username, result?.username)
    }

    @Test
    fun testExistsByUsername() {
        val userRepository = mockk<UserRepository>()

        val username = "testUser"

        every { userRepository.existsByUsername(username) } returns true

        val result = userRepository.existsByUsername(username)

        assertTrue(result)
    }
}