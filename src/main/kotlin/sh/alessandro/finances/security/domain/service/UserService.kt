package sh.alessandro.finances.security.domain.service

import sh.alessandro.finances.security.domain.models.User

interface UserService {
    fun findById(id: Long): User?
    fun findByUserName(username: String): User?
    fun existsByUsername(username: String): Boolean
    fun saveOne(user: User): User
}