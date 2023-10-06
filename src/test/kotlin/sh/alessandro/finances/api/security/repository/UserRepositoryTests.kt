package sh.alessandro.finances.api.security.repository

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import sh.alessandro.finances.api.security.domain.models.User
import sh.alessandro.finances.api.security.domain.persistence.UserRepository

class UserRepositoryTests {

    @Test
    fun testFindByUsername() {
        val userRepository = mock(UserRepository::class.java)

        val username = "testUser"
        val user = User(id = 1L, username = username, password = "testPassword")

        `when`(userRepository.findByUsername(username)).thenReturn(user)

        val result = userRepository.findByUsername(username)

        assertNotNull(result)
        assertEquals(username, result?.username)
    }

    @Test
    fun testExistsByUsername() {
        val userRepository = mock(UserRepository::class.java)

        val username = "testUser"

        `when`(userRepository.existsByUsername(username)).thenReturn(true)

        val result = userRepository.existsByUsername(username)

        assertTrue(result)
    }
}