package sh.alessandro.finances.api.calculator.domain.service

import sh.alessandro.finances.api.calculator.domain.models.Client
import sh.alessandro.finances.api.calculator.dto.NewClientDto

interface ClientService {
    fun findById(id: Long): Client?
    fun create(client: NewClientDto): Client
}