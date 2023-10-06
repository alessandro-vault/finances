package sh.alessandro.finances.api.calculator.domain.persistence

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PlanRepository : JpaRepository<sh.alessandro.finances.api.calculator.domain.models.Plan, UUID>