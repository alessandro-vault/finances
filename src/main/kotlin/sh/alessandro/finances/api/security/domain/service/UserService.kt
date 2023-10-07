package sh.alessandro.finances.api.security.domain.service

import sh.alessandro.finances.api.security.domain.models.User
import java.util.*

interface UserService {
    fun findById(id: UUID): User?
    fun findByUserName(username: String): User?
    fun existsByUsername(username: String): Boolean
    fun saveOne(user: User): User
}