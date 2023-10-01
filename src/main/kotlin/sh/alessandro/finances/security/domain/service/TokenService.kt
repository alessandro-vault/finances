package sh.alessandro.finances.security.domain.service

import sh.alessandro.finances.security.domain.User

interface TokenService {
    fun createToken(user: User): String
    fun parseToken(token: String): User?
}