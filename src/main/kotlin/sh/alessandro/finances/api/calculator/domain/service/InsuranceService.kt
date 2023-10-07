package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Insurance
import java.util.*

interface InsuranceService {
    fun getOne(id: UUID): Insurance
    fun createOne(insurance: Insurance): Insurance
}