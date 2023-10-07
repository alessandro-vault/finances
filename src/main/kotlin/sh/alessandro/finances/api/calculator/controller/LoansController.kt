package sh.alessandro.finances.api.calculator.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import sh.alessandro.finances.api.calculator.domain.models.Loan
import sh.alessandro.finances.api.calculator.domain.service.LoanService

@RestController
@RequestMapping("api/v1/loans")
class LoansController(
    private val loanService: LoanService
) {

    @GetMapping("{id}")
    fun getLoan(@PathVariable id: Long) : ResponseEntity<Loan> {
        return try {
            val loan = loanService.getOne(id)
            ResponseEntity.ok(loan)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}