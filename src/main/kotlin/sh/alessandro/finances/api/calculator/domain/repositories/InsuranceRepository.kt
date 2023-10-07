package sh.alessandro.finances.api.calculator.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import sh.alessandro.finances.api.calculator.domain.models.Insurance
import java.util.*

interface InsuranceRepository : JpaRepository<Insurance, UUID>