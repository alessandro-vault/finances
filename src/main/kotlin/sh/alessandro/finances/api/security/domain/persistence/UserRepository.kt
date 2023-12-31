package sh.alessandro.finances.api.security.domain.persistence

import org.springframework.data.jpa.repository.JpaRepository
import sh.alessandro.finances.api.security.domain.models.User
import java.util.*

interface UserRepository : JpaRepository<User, UUID> {
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
}