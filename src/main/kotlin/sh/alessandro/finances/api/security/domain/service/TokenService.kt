package sh.alessandro.finances.api.security.domain.service

import sh.alessandro.finances.api.security.domain.models.User

interface TokenService {
    fun createToken(user: User): String
    fun parseToken(token: String): User?
}