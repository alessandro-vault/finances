package sh.alessandro.finances.api.security.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.domain.persistence.UserRepository

class UserServiceTests {

    private val userRepository = mock(UserRepository::class.java)

    private val userService = UserServiceImpl(userRepository)

    @Test
    fun testFindById() {
        val userId = 1L
        val user = User(id = userId, username = "test", password = "test")

        `when`(userRepository.findById(userId)).thenReturn(java.util.Optional.of(user))

        val result = userService.findById(userId)

        assertEquals(userId, result?.id)
        assertEquals("test", result?.username)
    }

    @Test
    fun testFindByUserName() {
        val username = "test"
        val user = User(id = 1L, username = username, password = "test")

        `when`(userRepository.findByUsername(username)).thenReturn(user)

        val result = userService.findByUserName(username)

        assertEquals(username, result?.username)
    }

    @Test
    fun testExistsByUsername() {
        val username = "test"

        `when`(userRepository.existsByUsername(username)).thenReturn(true)

        val result = userService.existsByUsername(username)

        assertEquals(true, result)
    }

    @Test
    fun testSaveOne() {
        val user = User(id = 1L, username = "test", password = "test")

        `when`(userRepository.save(user)).thenReturn(user)

        val result = userService.saveOne(user)

        assertEquals(user, result)
    }

    @Test
    fun testFindByUserName_UsernameNotFound() {
        val username = "test"

        `when`(userRepository.findByUsername(username)).thenReturn(null)

        val result = userService.findByUserName(username)

        assertEquals(result, null)
    }
}