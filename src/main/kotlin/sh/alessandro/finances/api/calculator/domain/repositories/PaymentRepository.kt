package sh.alessandro.finances.api.calculator.domain.repositories

import org.springframework.data.jpa.repository.JpaRepository
import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.models.Plan
import java.util.*

interface PaymentRepository : JpaRepository<Payment, UUID> {
    fun getAllByPlan(plan: Plan): List<Payment>
}