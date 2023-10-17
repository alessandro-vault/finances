package sh.alessandro.finances.api.calculator.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.domain.models.Plan
import java.util.*

interface PlanRepository : JpaRepository<Plan, UUID>{
    fun findByClientUserUsername(username: String): List<Plan>
    fun findByClient(client: Client): List<Plan>
}