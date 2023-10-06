package sh.alessandro.finances.api.calculator.domain.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.*

@Entity
@Table(name = "insurances")
@AllArgsConstructor
@NoArgsConstructor
data class Insurance(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "type")
    var type: sh.alessandro.finances.api.calculator.domain.enums.InsuranceType,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @ManyToOne()
    @JoinColumn(name = "plan_id")
    var plan: Plan? = null,
)