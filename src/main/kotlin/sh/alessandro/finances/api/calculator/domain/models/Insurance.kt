package sh.alessandro.finances.api.calculator.domain.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import sh.alessandro.finances.api.calculator.domain.enums.InsuranceType
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
    var type: InsuranceType,

    @Column(name = "percentage")
    var percentage: Double? = null,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    @ManyToOne()
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    var plan: Plan? = null,
)