package sh.alessandro.finances.api.calculator.domain.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import sh.alessandro.finances.api.calculator.domain.enums.GracePeriodType
import java.util.*

@Entity
@Table(name = "grace_periods")
@NoArgsConstructor
@AllArgsConstructor
data class GracePeriod (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column
    var type: GracePeriodType = GracePeriodType.PARTIAL,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @ManyToOne
    @JoinColumn(name = "plan_id")
    var plan: Plan? = null
)