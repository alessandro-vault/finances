package sh.alessandro.finances.builders

import sh.alessandro.finances.api.calculator.domain.models.Payment
import java.time.LocalDate

data class PaymentBuilder(
    var number: Int,
    var paymentDate: LocalDate,
    var amortization: Double,
    var interest: Double,
    var lifeInsurance: Double,
    var carInsurance: Double,
    var postage: Double,
    var balance: Double
) {
    fun build() : Payment {
        return Payment(
            number = number,
            paymentDate = paymentDate,
            amortization = amortization,
            interest = interest,
            lifeInsurance = lifeInsurance,
            carInsurance = carInsurance,
            postage = postage,
            balance = balance
        )
    }
}