package sh.alessandro.finances.api.calculator.domain.models

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.util.*


@Entity
@Table(name = "loans")
data class Loan (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(name = "initial_amount")
    @NotEmpty
    @Min(value = 0)
    @Max(value = 100)
    var initialAmount: Double,

    @Column(name = "down_payment")
    @Min(value = 5)
    @Max(value = 99)
    var downPaymentPercentage: Float,

    @Column(name = "rate")
    @NotEmpty @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    var rate: Float,

    @Column(name = "rate_type")
    var rateType: sh.alessandro.finances.api.calculator.domain.enums.RateType = sh.alessandro.finances.api.calculator.domain.enums.RateType.EFFECTIVE,

    @Column(name = "currency")
    var currency: sh.alessandro.finances.api.calculator.domain.enums.Currency = sh.alessandro.finances.api.calculator.domain.enums.Currency.PEN,

    @Column(name = "term")
    @Min(value = 1)
    var term: UShort,

    @Column(name = "date")
    @NotNull
    var date: Date,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    //Relationships

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    var client: Client? = null,

    @OneToOne(mappedBy = "loan", cascade = [CascadeType.ALL])
    var plan: Plan? = null,
)
{
    fun downPayment(): Double {
        return (this.initialAmount * (this.downPaymentPercentage.toDouble() / 100))
    }

}