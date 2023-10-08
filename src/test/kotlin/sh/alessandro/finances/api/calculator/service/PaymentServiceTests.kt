package sh.alessandro.finances.api.calculator.service

import io.mockk.every
import io.mockk.spyk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import sh.alessandro.finances.api.calculator.domain.enums.Currency
import sh.alessandro.finances.api.calculator.domain.models.Payment
import sh.alessandro.finances.api.calculator.domain.repositories.PaymentRepository
import sh.alessandro.finances.api.calculator.domain.service.PaymentService
import sh.alessandro.finances.builders.PaymentBuilder
import sh.alessandro.finances.builders.PlanBuilder
import java.time.LocalDate

class PaymentServiceTests {
    private val plan = PlanBuilder(
        totalAmount = 20000.00,
        downPaymentPercentage = 10.0f,
        rate = 0.1357,
        currency = Currency.USD,
        term = 24u,
        date = LocalDate.of(2023, 9, 22),
        postage = 5.0,
        lifeInsurancePercentage = 0.00015,
        carInsurancePercentage = 0.0010
    ).build()

    private val payments : List<Payment>  = listOf(
        PaymentBuilder(1, LocalDate.of(2023, 10, 22), 660.93, 191.89, 2.70, 20.00, 5.00, 17339.07).build(),
        PaymentBuilder(2, LocalDate.of(2023, 11, 21), 668.08, 184.84, 2.60, 20.00, 5.00, 16670.99).build(),
        PaymentBuilder(24, LocalDate.of(2025, 11, 22), 846.37, 9.02, 0.13, 20.00, 5.00, 0.00).build()
    )
    @Test
    fun createPayments() {
        val paymentRepository = spyk<PaymentRepository>()

        every { paymentRepository.saveAll(any<List<Payment>>()) } returns payments

        val paymentService: PaymentService = PaymentServiceImpl(paymentRepository)


        val payments = paymentService.createMany(plan)

        assertEquals(191.89, payments.first().interest, 0.01)
        assertEquals(9.02, payments.last().interest, 0.01)

        assertEquals(0.00, payments.last().balance)
    }

    @Test
    fun buildFirstPayment() {
        val paymentRepository = spyk<PaymentRepository>()

        every { paymentRepository.saveAll(any<List<Payment>>()) } returns payments

        val paymentService: PaymentService = PaymentServiceImpl(paymentRepository)

        val payment = paymentService.buildFirstPayment(plan, plan.postage)

        assertEquals(1, payment.number)
        assertEquals(LocalDate.of(2023, 10, 22), payment.paymentDate)
        assertEquals(191.89, payment.interest, 0.01)
        assertEquals(660.93, payment.amortization, 0.01)
        assertEquals(2.70, payment.lifeInsurance, 0.01)
        assertEquals(20.00, payment.carInsurance, 0.01)
        assertEquals(17339.07, payment.balance, 0.01)
    }
}