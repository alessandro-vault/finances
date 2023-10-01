package sh.alessandro.finances.security.domain.persistence

import org.springframework.data.jpa.repository.JpaRepository
import sh.alessandro.finances.security.domain.User

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
}