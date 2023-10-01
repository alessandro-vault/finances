package sh.alessandro.finances.security.domain.service

interface HashService {
    fun checkBcrypt(password: String, hash: String): Boolean
    fun hashBcrypt(password: String): String
}