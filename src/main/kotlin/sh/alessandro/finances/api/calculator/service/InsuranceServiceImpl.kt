package sh.alessandro.finances.api.calculator.service

import org.springframework.stereotype.Service
import sh.alessandro.finances.api.calculator.domain.models.Insurance
import sh.alessandro.finances.api.calculator.domain.service.InsuranceService
import java.util.*

@Service
class InsuranceServiceImpl : InsuranceService {
    override fun getOne(id: UUID): Insurance {
        TODO("Not yet implemented")
    }

    override fun createOne(insurance: Insurance): Insurance {
        TODO("Not yet implemented")
    }
}