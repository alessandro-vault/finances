package sh.alessandro.finances.api.calculator.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import sh.alessandro.finances.api.calculator.domain.models.Client

interface ClientRepository : JpaRepository<Client, Long> {
    fun findByUserUsername(username: String): Client?
}