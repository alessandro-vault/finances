package sh.alessandro.finances.security.domain.service

import sh.alessandro.finances.security.domain.models.User

interface TokenService {
    fun createToken(user: User): String
    fun parseToken(token: String): User?
}