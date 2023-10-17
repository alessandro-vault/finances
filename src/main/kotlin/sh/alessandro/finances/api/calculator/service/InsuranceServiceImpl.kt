package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Insurance
import sh.alessandro.finances.api.calculator.domain.repositories.InsuranceRepository
import sh.alessandro.finances.api.calculator.domain.service.InsuranceService
import java.util.*

@Service
class InsuranceServiceImpl(
    private val insuranceRepository: InsuranceRepository
) : InsuranceService {
    override fun getOne(id: UUID): Insurance {
        return insuranceRepository.findById(id).orElseThrow()
    }

    override fun createOne(insurance: Insurance): Insurance {
        return insuranceRepository.save(insurance)
    }
}