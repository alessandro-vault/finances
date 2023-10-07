package sh.alessandro.finances.api.calculator.domain.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import jakarta.validation.constraints.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import sh.alessandro.finances.api.calculator.domain.enums.Currency
import sh.alessandro.finances.api.calculator.domain.enums.RateType
import java.time.LocalDate


@Entity
@Table(name = "loans")
@AllArgsConstructor
@NoArgsConstructor
data class Loan(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "initial_amount")
    @NotEmpty
    @Min(value = 0)
    var totalAmount: Double,

    @Column(name = "down_payment")
    @Min(value = 5) @Max(value = 99)
    var downPaymentPercentage: Float,

    @Column(name = "rate")
    @NotEmpty @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    var rate: Double,

    @Column(name = "rate_type")
    var rateType: RateType = RateType.EFFECTIVE,

    @Column(name = "currency")
    var currency: Currency = Currency.PEN,

    @Column(name = "term")
    @Min(value = 1)
    var term: UShort,

    @Column(name = "date")
    @NotNull
    var date: LocalDate,

    @Column(name = "created_at")
    @JsonIgnore
    var createdAt: LocalDate = LocalDate.now(),

    //Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    var client: Client? = null,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "plan_id")
    @JsonIgnore
    var plan: Plan? = null,
)
{
    fun downPaymentAmount(): Double {
        return (this.totalAmount * (this.downPaymentPercentage.toDouble() / 100))
    }

    fun totalDebt(): Double {
        return (this.totalAmount - this.downPaymentAmount())
    }
}