package sh.alessandro.finances.api.calculator.domain.models

import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.util.*

@Entity
@Table(name = "plans")
@AllArgsConstructor
@NoArgsConstructor
data class Plan(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "postage")
    var postage: Double,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    // Relationships
    @OneToMany(mappedBy = "plan", cascade = [CascadeType.ALL], orphanRemoval = true)
    var insurances: List<Insurance> = listOf(),

    @OneToMany(mappedBy = "plan", cascade = [CascadeType.ALL])
    var payments: List<Payment> = listOf(),

    @OneToOne(mappedBy = "plan", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "loan_id")
    var loan: Loan? = null,
    )