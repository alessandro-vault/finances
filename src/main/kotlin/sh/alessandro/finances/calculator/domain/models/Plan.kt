package sh.alessandro.finances.calculator.domain.models

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
    var id: UUID? = null,

    @Column(name = "postage")
    var postage: Double,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    // Relationships
    @OneToMany(mappedBy = "plan", cascade = [CascadeType.ALL])
    var payments: List<Payment> = listOf(),

    @OneToOne()
    @JoinColumn(name = "loan_id")
    var loan: Loan,

    )