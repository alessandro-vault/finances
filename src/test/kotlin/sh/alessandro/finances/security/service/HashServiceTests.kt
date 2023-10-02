package sh.alessandro.finances.security.service

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test

class HashServiceTests {

    private val hashService = HashServiceImpl()

    @Test
    fun testHashBcrypt() {
        val password = "password"
        val hash = hashService.hashBcrypt(password)
        assert(hashService.checkBcrypt(password, hash))
    }

    @Test
    fun testCheckBcryptWithMatchingPassword() {
        val password = "password"
        val hash = hashService.hashBcrypt(password)
        assert(hashService.checkBcrypt(password, hash))
    }

    @Test
    fun testCheckBcryptWithNonMatchingPassword() {
        val password = "password"
        val hash = hashService.hashBcrypt(password)
        assertFalse(hashService.checkBcrypt("wrongPassword", hash))
    }
}