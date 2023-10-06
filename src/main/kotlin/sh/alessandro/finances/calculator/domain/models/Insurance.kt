package sh.alessandro.finances.calculator.domain.models

import jakarta.persistence.*
import sh.alessandro.finances.calculator.domain.enums.InsuranceType
import java.util.*

data class Insurance(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @Column(name = "type")
    var type: InsuranceType,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @ManyToOne()
    @JoinColumn(name = "plan_id")
    var plan: Plan,
)