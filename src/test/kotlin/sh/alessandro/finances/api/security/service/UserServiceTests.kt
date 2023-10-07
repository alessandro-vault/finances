package sh.alessandro.finances.api.security.service

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.domain.persistence.UserRepository
import java.util.*

class UserServiceTests {

    private val userRepository = mockk<UserRepository>()

    private val userService = UserServiceImpl(userRepository)

    @Test
    fun testFindById() {
        val userId = UUID.randomUUID()
        val user = User(id = userId, username = "test", password = "test")

        every { userRepository.findById(userId) } returns Optional.of(user)

        val result = userService.findById(userId)

        assertEquals(userId, result?.id)
        assertEquals("test", result?.username)
    }

    @Test
    fun testFindByUserName() {
        val username = "test"
        val user = User(id = UUID.randomUUID(), username = username, password = "test")

        every { userRepository.findByUsername(username) } returns user

        val result = userService.findByUserName(username)

        assertEquals(username, result?.username)
    }

    @Test
    fun testExistsByUsername() {
        val username = "test"

        every { userRepository.existsByUsername(username) } returns true

        val result = userService.existsByUsername(username)

        assertEquals(true, result)
    }

    @Test
    fun testSaveOne() {
        val user = User(id = UUID.randomUUID(), username = "test", password = "test")

        every { userRepository.save(user) } returns user

        val result = userService.saveOne(user)

        assertEquals(user, result)
    }

    @Test
    fun testFindByUserName_UsernameNotFound() {
        val username = "test"

        every { userRepository.findByUsername(username) } returns null

        val result = userService.findByUserName(username)

        assertEquals(result, null)
    }
}