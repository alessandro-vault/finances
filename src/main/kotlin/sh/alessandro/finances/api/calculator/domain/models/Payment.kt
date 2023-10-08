package sh.alessandro.finances.api.calculator.domain.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import java.time.LocalDate
import java.util.*

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
data class Payment (
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "number")
    var number: Int,

    @Column(name = "date")
    var paymentDate: LocalDate,

    @Column(name = "amortization")
    var amortization: Double,

    @Column(name = "interest")
    var interest: Double,

    @Column(name = "life_insurance")
    var lifeInsurance: Double,

    @Column(name = "car_insurance")
    var carInsurance: Double,

    @Column(name = "postage")
    var postage: Double,

    @Column(name = "balance")
    var balance: Double,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    var plan: Plan? = null,
)