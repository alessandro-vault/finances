package sh.alessandro.finances.api.calculator.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.security.domain.models.User

interface ClientRepository : JpaRepository<Client, Long> {
    fun findByUserUsername(username: String): Client?
    fun findByUser(user: User): Client?
}