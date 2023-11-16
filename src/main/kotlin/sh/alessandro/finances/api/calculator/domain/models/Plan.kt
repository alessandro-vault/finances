package sh.alessandro.finances.api.calculator.domain.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.NoArgsConstructor
import org.apache.poi.ss.formula.functions.FinanceLib
import org.apache.poi.ss.formula.functions.Irr
import sh.alessandro.finances.api.calculator.domain.enums.InsuranceType
import java.util.*
import kotlin.math.pow

@Entity
@Table(name = "plans")
@AllArgsConstructor
@NoArgsConstructor
data class Plan(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @Column(name = "title")
    var title: String = "",

    @Column(name = "postage")
    @JsonIgnore
    var postage: Double,

    @Column(name = "created_at")
    var createdAt: Date = Date(),

    // Relationships
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    @JsonIgnore
    var client: Client? = null,

    @OneToMany(mappedBy = "plan", cascade = [CascadeType.ALL], orphanRemoval = true)
    var insurances: List<Insurance> = listOf(),

    @OneToMany(mappedBy = "plan", cascade = [CascadeType.ALL])
    var payments: List<Payment> = listOf(),

    @OneToOne(mappedBy = "plan", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "loan_id")
    var loan: Loan? = null,
    ) {

    fun monthlyRate(): Double {
        return ((1 + this.loan?.rate!!).pow(30.0 / 360) - 1)
    }

    fun monthlyPayment(): Double {
        return FinanceLib.pmt(
            this.monthlyRate() + this.lifeInsurance(),
            this.loan?.term!!.toDouble(),
            -this.loan?.totalDebt()!!,
            0.0,
            false
        ) + this.postage + (this.carInsurance() * this.loan?.totalAmount!!)
    }

    fun lifeInsurance(): Double {
        return this.insurances.find { it.type == InsuranceType.LIFE }?.percentage!!
    }

    fun carInsurance(): Double {
        return this.insurances.find { it.type == InsuranceType.CAR }?.percentage!!
    }

    fun irr(): Double {
        return Irr.irr(
            Array((loan!!.term + 1u).toInt()) {
                if (it == 0) this.loan?.totalDebt()!! else -this.monthlyPayment()
            }.toDoubleArray()
        )
    }
}